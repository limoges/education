#if defined(__APPLE__) || defined(__MACH__) || defined(MACOSX)
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdlib.h>
#include <math.h>

void Display();
void Resize(const GLint width, const GLint height);
void Timer(const GLint delay);
void Keyboard(unsigned char key, const GLint x, const GLint y);
void KeyboardSpecial(int key, int x, int y);
void Mouse(const GLint button, const GLint stat, const GLint x, const GLint y);
void MouseActiveMotion(const GLint x, const GLint y);

void initialize(GLint argc, char **argv);
void clearSharedMem();
void setCamera(
        const GLfloat x, const GLfloat y, const GLfloat z,
        const GLfloat target_x, const GLfloat target_y, const GLfloat target_z
);
void Draw();
GLfloat getRandomClampf();
void randomizeClearColor();

GLboolean mouseLeftDown, mouseRightDown, mouseMiddleDown;
GLfloat mouseX, mouseY, cameraAngleX, cameraAngleY, cameraDistance;
GLint drawMode = 0;

GLfloat vertices[] = 
{
    1,1,1,  -1,1,1,  -1,-1,1,  1,-1,1,
    1,1,1,  1,-1,1,  1,-1,-1,  1,1,-1,
    1,1,1,  1,1,-1,  -1,1,-1,  -1,1,1,
    -1,1,1,  -1,1,-1,  -1,-1,-1,  -1,-1,1,
    -1,-1,-1,  1,-1,-1,  1,-1,1,  -1,-1,1,
    1,-1,-1,  -1,-1,-1,  -1,1,-1,  1,1,-1
};

GLfloat normals[] =
{
    0,0,1,  0,0,1,  0,0,1,  0,0,1,
    1,0,0,  1,0,0,  1,0,0, 1,0,0,
    0,1,0,  0,1,0,  0,1,0, 0,1,0,
    -1,0,0,  -1,0,0, -1,0,0,  -1,0,0,
    0,-1,0,  0,-1,0,  0,-1,0,  0,-1,0,
    0,0,-1,  0,0,-1,  0,0,-1,  0,0,-1
};

GLfloat colors[] =
{
    1,1,1,  1,1,0,  1,0,0,  1,0,1,
    1,1,1,  1,0,1,  0,0,1,  0,1,1,
    1,1,1,  0,1,1,  0,1,0,  1,1,0,
    1,1,0,  0,1,0,  0,0,0,  1,0,0,
    0,0,0,  0,0,1,  1,0,1,  1,0,0,
    0,0,1,  0,0,0,  0,1,0,  0,1,1
};

GLubyte indices[] =
{
    0,1,2,3,
    4,5,6,7,
    8,9,10,11,
    12,13,14,15,
    16,17,18,19,
    20,21,22,23
};

GLfloat rotation_matrix[] =
{
    0, -1, 0,
    1, 0, 0,
    0, 0, 1
};
void Draw()
{
    glEnableClientState(GL_NORMAL_ARRAY);
    glEnableClientState(GL_COLOR_ARRAY);
    glEnableClientState(GL_VERTEX_ARRAY);
    glNormalPointer(GL_FLOAT, 0, normals);
    glColorPointer(3, GL_FLOAT, 0, colors);
    glVertexPointer(3, GL_FLOAT, 0, vertices);

    glPushMatrix();
    glTranslatef(2, 2, 0);

    glDrawArrays(GL_QUADS, 0, 24);

    glPopMatrix();

    glDisableClientState(GL_VERTEX_ARRAY);
    glDisableClientState(GL_COLOR_ARRAY);
    glDisableClientState(GL_NORMAL_ARRAY);
}

GLint main(GLint argc, char **argv)
{
    mouseLeftDown = mouseRightDown = mouseMiddleDown = false;
    initialize(argc, argv);
    glutMainLoop();

    return EXIT_SUCCESS;
}

void initialize(GLint argc, char **argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH | GLUT_STENCIL);
    glutInitWindowSize(800, 600);
    glutCreateWindow("Exercice 3");
    
    glutDisplayFunc(Display);
    glutTimerFunc(100, Timer, 100);
    glutReshapeFunc(Resize);
    glutKeyboardFunc(Keyboard);
    glutMouseFunc(Mouse);
    glutMotionFunc(MouseActiveMotion);

    glShadeModel(GL_SMOOTH);

    glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    glEnable(GL_CULL_FACE);

    randomizeClearColor();
    setCamera(0, 0, 10, 0, 0, 0);;
}

void setCamera( GLfloat x, GLfloat y, GLfloat z,
                GLfloat target_x, GLfloat target_y, GLfloat target_z)
{
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    gluLookAt(  x, y, z,
                target_x, target_y, target_z,
                0, 0, 0
    ); // eye(x,y,z), focal(x,y,z), up(x,y,z)
}

void Display()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    glPushMatrix();
    glTranslatef(0, 0, cameraDistance);
    glRotatef(cameraAngleX, 1, 0, 0);
    glRotatef(cameraAngleY, 0, 1, 0);
    Draw();
    glPopMatrix();
    glutSwapBuffers();
}

void Resize(const GLint w, const GLint h)
{
    glViewport(0, 0, (GLsizei) w, (GLsizei) h);
    
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(60.0f, (GLfloat)(w)/h, 1.0f, 1000.0f);
    glMatrixMode(GL_MODELVIEW);
}

void Timer(GLint delay)
{
    glutTimerFunc(delay, Timer, delay);
    // Rotate the cube on itself

    glutPostRedisplay();
}

void Keyboard(unsigned char key, const GLint x, const GLint y)
{
    switch(key)
    {
        case 'q':
        case 'Q':
            exit(EXIT_SUCCESS);
            break;
		case 'c':
		case 'C':
			randomizeClearColor();
			break;
        case 'a':
        case 'A':
            break;
        case 'w':
        case 'W':
            break;
        case 'd':
        case 'D':
            break;
        case 's':
        case 'S':
            break;
        default:
            printf("key not registered\n");
    }
    glutPostRedisplay();
}

void KeyboardSpecial(int key, int x, int y)
{
    switch (key)
    {
        case GLUT_KEY_LEFT:
            //setCamera(x, y, z, target_x, target_y, target_z);
            break;
        case GLUT_KEY_UP:
            break;
        case GLUT_KEY_RIGHT:
            break;
        case GLUT_KEY_DOWN:
            break;
        default:
            printf("special key not registered\n");
    }
}

void Mouse(const GLint button, const GLint state, const GLint x, const GLint y)
{
    mouseX = x;
    mouseY = y;

    if (state == GLUT_DOWN)
    {
        switch (button)
        {
            case GLUT_LEFT_BUTTON:
                mouseLeftDown = true;
                break;
            case GLUT_RIGHT_BUTTON:
                mouseRightDown = true;
                break;
            case GLUT_MIDDLE_BUTTON:
                mouseMiddleDown = true;
                break;
        }
    }
    else
    {
        switch (button)
        {
            case GLUT_LEFT_BUTTON:
                mouseLeftDown = false;
                break;
            case GLUT_RIGHT_BUTTON:
                mouseRightDown = false;
                break;
            case GLUT_MIDDLE_BUTTON:
                mouseMiddleDown = false;
                break;
        }
    }
}


void MouseActiveMotion(GLint x, GLint y)
{
    if (mouseLeftDown)
    {
        cameraAngleY += (x - mouseX);
        cameraAngleX += (y - mouseY);
        mouseX = x;
        mouseY = y;
    }
    if (mouseRightDown)
    {
        cameraDistance += (y - mouseY) * 0.2f;
        mouseY = y;
    }
    glutPostRedisplay();
}

GLfloat getRandomClampf()
{
    return rand() / (((GLfloat) RAND_MAX) + 1);
}

void randomizeClearColor()
{
    GLfloat red = getRandomClampf();
    GLfloat green = getRandomClampf();
    GLfloat blue = getRandomClampf();
    glClearColor(red, green, blue, 1.0);
}
