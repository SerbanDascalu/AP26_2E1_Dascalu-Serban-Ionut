public class Matrix2D
{
    private static final int PRINT_LIMIT = 120;
    private static final int WHITE = 255;
    private static final int DARK  = 0;

    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("java Matrix2D <n> <rectangle|circle>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        String forma = args[1].toLowerCase();

        long start = System.nanoTime();

        int[][] img;
        if (forma.equals("rectangle"))
        {
            img = darkRectangleOnWhite(n);
        } else
            if (forma.equals("circle"))
        {
            img = whiteCircleOnDark(n);
        } else
        {
            System.out.println("Unknown");
            return;
        }

        long timeNs = System.nanoTime()-start;

        if (n <= PRINT_LIMIT)
        {
            System.out.println("n = " + n + ", forma = " + forma);
            System.out.println(prettyString(img));
        }

        System.out.printf("Build time: %,d ns (%.3f ms)%n", timeNs, timeNs / 1_000_000.0);
    }

    private static int[][] darkRectangleOnWhite(int n)
    {
        int[][] m = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                m[i][j] = WHITE;

        int top = n/5, left = n/5;
        int bottom = n-n/5, right = n-n/ 5;

        for (int i = top; i < bottom; i++)
            for (int j = left; j < right; j++)
                m[i][j] = DARK;

        return m;
    }

    private static int[][] whiteCircleOnDark(int n)
    {
        int[][] m = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                m[i][j] = DARK;

        int cx=n/2, cy = n/2;
        int r = n/3;
        int r2 = r*r;

        for (int i = 0; i < n; i++)
        {
            int dx = i-cx;
            for (int j = 0; j < n; j++)
            {
                int dy = j-cy;
                if (dx*dx + dy*dy <= r2)
                {
                    m[i][j] = WHITE;
                }
            }
        }
        return m;
    }

    private static String prettyString(int[][] m)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m.length; i++)
        {
            for (int j = 0; j < m.length; j++)
            {
                char c;
                if (m[i][j] < 85)
                {
                    c = '█';
                } else
                    if (m[i][j] < 170)
                   {
                      c = '▒';
                   } else
                     {
                       c = ' ';
                     }
                sb.append(c).append(c);
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}