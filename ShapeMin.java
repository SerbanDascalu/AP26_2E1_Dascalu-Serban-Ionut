//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ShapeMin
{

    static class Box
    {
        int minL = Integer.MAX_VALUE;
        int maxL = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE;
        int maxC = Integer.MIN_VALUE;

        void add(int l, int c)
        {
            minL = Math.min(minL, l);
            maxL = Math.max(maxL, l);
            minC = Math.min(minC, c);
            maxC = Math.max(maxC, c);
        }
    }

    static boolean isShape(int val, int bg)
    {
        return val != bg;
    }

    static Box boundingBox(int[][] m, int bg)
    {
        Box b= new Box();
        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m[i].length; j++)
            {
                if (isShape(m[i][j], bg))
                    b.add(i, j);
            }
        }
        return b;
    }

    static boolean[][] boundary(int[][] m, int bg)
    {
        int n = m.length;
        boolean[][] contur = new boolean[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {

                if (!isShape(m[i][j], bg))
                    continue;

                if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
                {
                    contur[i][j] = true;
                    continue;
                }

                if (!isShape(m[i - 1][j], bg) || !isShape(m[i + 1][j], bg) || !isShape(m[i][j - 1], bg) || !isShape(m[i][j + 1], bg))
                {
                    contur[i][j] = true;
                }
            }
        }
        return contur;
    }


    public static void main(String[] args)
    {
        int[][] m = {
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,1,1,1,0},
                {0,0,1,0,0},
                {0,0,0,0,0},
        };
        int bg=0;

        Box b = boundingBox(m, bg);
        System.out.printf("Bounding box: lin[%d si %d], col[%d si %d]%n", b.minL, b.maxL, b.minC, b.maxC);

        boolean[][] e = boundary(m, bg);
        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m[i].length; j++)
            {
                if (e[i][j])
                {
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}