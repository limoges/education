#define COL 20
#define LIN 10

int main()
{
  int i, x, y;
  for (i = 0; i < COL*LIN; i++)
  {
    x = i / COL;
    y = i % COL;
    printf("%d : %d, %d\n", i, x, y);
  }
  return 0;
}
