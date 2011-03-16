#if defined(__APPLE__)
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>
#else
#include <GL/gl.h>
#include <GL/glu.h>
#include <GLUT/glut.h>
#endif

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include <float.h>

typedef struct rect 
{
    int Width;
    int Height;
    rect(int w, int h) : Width(w), Height(h) {}
} rect;

typedef struct vec3d
{
    GLdouble X;
    GLdouble Y;
    GLdouble Z;
    vec3d(GLdouble x, GLdouble y, GLdouble z) : X(x), Y(y), Z(z) {}
} vec3d;

void Initalize();
void Display();
void Resize(const int x, const int y);
void Special(int key, int x, int y);
void Keyboard(unsigned char key, int x, int y);
void Mouse(int button, int state, int x, int y);
void ActiveMotion(int x, int y);
void randomizeClearColor();
void findClosest(double x, double y);
vec3d getOpenGLCoordFromScreenCoord(const int x, const int y);

float vertices[6] = { -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f };
float size = 0.01f;
bool selected = false;
float * vertex = NULL;

int main(int argc, char ** argv)
{
    srand(time(NULL));

    rect window(500, 500);
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(window.Width, window.Height);

	glutInitWindowPosition(10,10);
	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);
	glutCreateWindow("Exercice 3 Image");

	glutDisplayFunc(Display);
	glutReshapeFunc(Resize);
	glutSpecialFunc(Special);
	glutKeyboardFunc(Keyboard);
    glutMouseFunc(Mouse);
    glutMotionFunc(ActiveMotion);
	randomizeClearColor();
    Initalize();
	glutMainLoop();
}

void Initalize()
{
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    glShadeModel(GL_SMOOTH);
}

void Display()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();
	glMatrixMode(GL_MODELVIEW);

    glColor3f(0.0f, 0.0f, 0.0f);
    glBegin(GL_TRIANGLES);
        glColor3f(1.0f, 0.0f, 0.0f);
        glVertex3f(vertices[0], vertices[1], 0.0f);
        glColor3f(0.0f, 1.0f, 0.0f);
        glVertex3f(vertices[2], vertices[3], 0.0f);
        glColor3f(0.0f, 0.0f, 1.0f);
        glVertex3f(vertices[4], vertices[5], 0.0f);
    glEnd();
    
    if (selected)
    {
        glColor3f(0.0f, 0.0f, 0.0f);
        glBegin(GL_QUADS);
            glVertex3f(vertex[0] - size, vertex[1] - size, 0.0f);
            glVertex3f(vertex[0] - size, vertex[1] + size, 0.0f);
            glVertex3f(vertex[0] + size, vertex[1] + size, 0.0f);
            glVertex3f(vertex[0] + size, vertex[1] - size, 0.0f);
        glEnd();
    }

	glutSwapBuffers();
}

void Resize(const GLint x, const GLint y)
{
	glViewport(0, 0, x, y);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, x, 0, y);
}

void Special(int key, GLint x, GLint y)
{
	switch (key)
	{
	  case GLUT_KEY_UP:
		   break;
	  case GLUT_KEY_DOWN:
		   break;
   	  case GLUT_KEY_LEFT:
		   break;
	  case GLUT_KEY_RIGHT:
		   break;
   	  case GLUT_KEY_PAGE_UP:
		   break;
	  case GLUT_KEY_PAGE_DOWN:
		   break;
	}
    glutPostRedisplay();
}

void Keyboard(unsigned char key, int x, int y)
{
	switch (key)
	{
	    case 'q':
        case 'Q':
            exit(EXIT_SUCCESS);
		    break;
        case 'c':
        case 'C':
            randomizeClearColor();
            break;
	}	
	glutPostRedisplay();
}

void Mouse(int button, int state, int x, int y)
{
    if (GLUT_UP)
    {
        switch (button)
        {
            case GLUT_LEFT_BUTTON:
                selected = false;
                vertex = NULL;
                vec3d mouse = getOpenGLCoordFromScreenCoord(x, y);
                findClosest(mouse.X, mouse.Y);
                break;
        }
        glutPostRedisplay();
    }
}

void ActiveMotion(int x, int y)
{
    if (vertex == NULL) return;
    vec3d mouse = getOpenGLCoordFromScreenCoord(x, y);
    vertex[0] = mouse.X;
    vertex[1] = mouse.Y;
    glutPostRedisplay();
}

float getRandomClampf()
{
    return rand() / (((float) RAND_MAX) + 1);
}

void randomizeClearColor()
{
    float red = getRandomClampf();
    float green = getRandomClampf();
    float blue = getRandomClampf();
    glClearColor(red, green, blue, 1.0);
}

GLdouble distance(GLdouble ax, GLdouble ay, GLdouble bx, GLdouble by)
{
    GLdouble distance = (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
    return distance;
}

void findClosest(GLdouble x, GLdouble y)
{
    GLdouble min = DBL_MAX, dist = DBL_MAX;
    int imin = 0;

    for (int i = 0, b = 0; b < 3; b++, i += 2)
    {
        dist = distance(vertices[i], vertices[i+1], x, y);
        if (dist < min)
        {
            min = dist;
            imin = i;
        }
    }
    if (min > 0.05) return;
    selected = true;
    vertex = &vertices[imin];
}

vec3d getOpenGLCoordFromScreenCoord(const int x, const int y)
{
    GLint viewport[4];
    GLdouble modelview[16];
    GLdouble projection[16];
    GLfloat screenx, screeny, screenz;
    GLdouble posx, posy, posz;

    glGetDoublev(GL_MODELVIEW_MATRIX, modelview);
    glGetDoublev(GL_PROJECTION_MATRIX, projection);
    glGetIntegerv(GL_VIEWPORT, viewport);

    screenx = (GLfloat) x;
    screeny = (GLfloat) viewport[3] - (GLfloat) y;
    glReadPixels(x, (GLint) screeny, 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, &screenz);
    gluUnProject(screenx, screeny, screenz, modelview, projection, viewport, &posx, &posy, &posz);
    return vec3d(posx, posy, posz);
}
