#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>

#include <stdlib.h>
#include <time.h>

void Display();
void Resize(const int x, const int y);
void Special(int key, int x, int y);
void Keyboard(unsigned char key, int x, int y);
void randomizeClearColor();

typedef struct rect 
{
    int width;
    int height;
    rect(int w, int h) : width(w), height(h) {}
} rect;

int main(int argc, char ** argv)
{
    srand(time(NULL));

    rect window(800,600);
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(window.width, window.height);

	glutInitWindowPosition(10,10);
	glutInitWindowSize(500,500);
	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);
	glutCreateWindow("Exercice 3 Image");

	glutDisplayFunc(Display);
	glutReshapeFunc(Resize);
	glutSpecialFunc(Special);
	glutKeyboardFunc(Keyboard);
    
	glClearColor(1.0,0.5,1.0,1.0);
	glutMainLoop();
}

void Display()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);

    glColor3f(0.0f, 0.0f, 0.0f);
    glBegin(GL_TRIANGLES);
        glVertex3f(-1.0f, 0.0f, 0.0f);
        glVertex3f(1.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 1.0f, 0.0f);
    glEnd();

	glutSwapBuffers();
}

void Resize(const int x, const int y)
{
	glViewport(0,0,x,y);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0,x,0,y);
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
