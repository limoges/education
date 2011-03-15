#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>

void Display();
void Resize(const int x, const int y);
void Special(int key, int x, int y);
void Keyboard(unsigned char key, int x, int y);

struct
{
    int width;
    int height;
} rect;


int main(int argc, char ** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(window.width, window.height);

	glutInitWindowPosition(10,10);
	glutInitWindowSize(500,500);
	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);
	glutCreateWindow("Test Pipeline 3D");

	glutDisplayFunc(Display);
	glutReshapeFunc(Resize);
	glutSpecialFunc(Special);
	glutKeyboardFunc(Keyboard);
	glutMainLoop();
}

void Display()
{
	glClearColor(1.0,1.0,1.0,1.0);
	glClear(GL_COLOR_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);

    float vertices[24] =
    {
        -1, 1, 0,
        -1,-1, 0,
         1,-1, 0,
         1,-1, 1,
         1, 1, 1,
         1, 1, 0,
        -1, 1, 1
    };

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
	    case 'c':
		break;
	}	
	glutPostRedisplay();
}
