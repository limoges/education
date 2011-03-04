#include <unistd.h> // execl
#include <stdio.h>  // printf
#include <signal.h> // signal constants

pid_t msg1;
pid_t msg2;
int msg_counter = 0; // used to signal alternating processes

// The handler alternates between signaling msg1 and msg2
// msg1 and msg2 both signal this process back
// We use the counter to alternate
void handler(int sig)
{
   if (msg_counter == 0)
   {
      kill(msg2, SIGUSR2);
      msg_counter = 1;
   }
   else
   {
      kill(msg1, SIGUSR2);
      msg_counter = 0;
   }
   
}

int main(int argc, char** argv)
{
   // register our special signal function
   // SIGUSR1 : user defined signal 1
   signal(SIGUSR1, &handler);

   // start MSG1
   msg1 = fork();
   switch (msg1)
   {
      case -1://fork failed
         printf("Fork MSG1 failed");
         return 1;
      case 0:
         printf("Fork MSG1 successful");
         // start the program
         execl("msg1",//path
               "msg1",//name of the process
               NULL
         );
      break;
   }
   // start MSG2
   msg2 = fork();
   switch (msg2)
   {
      case -1://fork failed
         printf("Fork MSG2 failed");
         return 1;
      case 0:
         printf("Fork MSG2 successful");
         execl("msg2",//path
               "msg2",//name of the process
               NULL
         );
   }
   waitpid(-1, NULL, 0);
   return 0;
}
