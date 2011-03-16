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

typedef struct vec3f
{
    float X;
    float Y;
    float Z;
    vec3f(float x, float y, float z) : X(x), Y(y), Z(z) {}
} vec3f;

void Display();
void Resize(const int x, const int y);
void Special(int key, int x, int y);
void Keyboard(unsigned char key, int x, int y);
void Mouse(int button, int state, int x, int y);
void randomizeClearColor();
void findClosest(int x, int y);
vec3f getOpenGLCoordFromScreenCoord(const int x, const int y);

float vertices[6] = { -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f };
float size = 0.01f;
bool selected = false;
float * vertex = NULL;

int main(int argc, char ** argv)
{
    srand(time(NULL));

    rect window(800,600);
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
  
	glClearColor(1.0,0.5,1.0,1.0);
	glutMainLoop();
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

void Resize(const int x, const int y)
{
	glViewport(0, 0, x, y);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, x, 0, y);
}

void Special(int key, int x, int y)
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
    printf("(%d, %d)\n", x, y);
    if (GLUT_UP)
    {
        switch (button)
        {
            case GLUT_LEFT_BUTTON:
                findClosest(x, y);
                break;
        }
        glutPostRedisplay();
    }
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

float distance(int ax, int ay, int bx, int by)
{
    float distance = sqrt((ax - bx) * (ax - bx) + (ay - by) * (ay - by));
    return distance;
}

void findClosest(int x, int y)
{
    float min = FLT_MAX, dist = FLT_MAX;
    int imin = 0;

    for (int i = 0, b = 0; b < 3; b++, i += 2)
    {
        dist = distance(vertices[i], vertices[i], x, y);
        if (dist < min);
        {
            //printf("dist:%f", dist);
            min = dist;
            imin = i;
        }
    }
    //printf("distance(%d);", dist);
    selected = true;
    vertex = &vertices[imin];
    //printf("/[%f][%f]/\n", vertex[0], vertex[1]);
    fflush(stdout);
}

vec3f getOpenGLCoordFromScreenCoord(const int x, const int y)
{
    GLint viewport[4];
    GLdouble modelview[16];
    GLdouble projection[16];
    GLfloat screenx, screeny, screenz;
    GLfloat posx, posy, posz;

    glGetDoublev(GL_MODELVIEW_MATRIX, modelview);
    glGetDoublev(GL_PROJECTION_MATRIX, projection);
    glGetIntegerv(GL_VIEWPORT, viewport);

    screenx = (GLfloat) x;
    screeny = (GLfloat) viewport[3] - (GLfloat) y;
    glReadPixels(x, (GLint) screeny, 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, &screenz);
    gluUnProject(screenx, screeny, screenz, modelview, projection, viewport, posx, posy, posz);
    return vec3f(posx, posy, posz);
}
