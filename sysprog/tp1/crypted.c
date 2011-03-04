#include <unistd.h> // execl
#include <stdio.h>  // printf
#include <signal.h> // signal constants

char* str_msg1 = "msgc1";
char* str_msg2 = "msgc2";
char* str_dec  = "dec";

pid_t msg1;
pid_t msg2;
pid_t dec;
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
   int fdesc[2]; // create 2 file descriptor
   // 0 is the entry
   // 1 is the exit
   if (pipe(fdesc) == -1)
      return 1; // error creating the pipes

   // start MSGC1
   msg1 = fork();
   switch (msg1)
   {
      case -1://fork failed
         return 1;
      case 0:
         // start the program
         dup2(fdesc[1], 1);
         execl(str_msg1,//path
               str_msg1,//name of the process
               NULL
         );
      break;
   }
   // start MSGC1
   msg2 = fork();
   switch (msg2)
   {
      case -1://fork failed
         return 1;
      case 0:
         dup2(fdesc[1], 1);
         execl(str_msg2, str_msg2, NULL);
   }
   // start DEC
   dec = fork();
   switch (dec)
   {
      case -1://fork failed
        return 1; 
      case 0:
         dup2(fdesc[0], 0);
         execl(str_dec, str_dec, NULL);
   }
   //dup2(fdesc[1],1);
   //dup2(fdesc[0],0);
   waitpid(-1, NULL, 0);
   execlp("ps", "ps", NULL);
   return 0;
}
