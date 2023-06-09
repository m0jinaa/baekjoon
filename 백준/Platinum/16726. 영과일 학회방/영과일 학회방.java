import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int n, m;
	static boolean[] used;
	static int[] covered;
	static ArrayList<Integer>[] canCover;

	static boolean check(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= m)
			return false;
		return map[x][y] != 'X';
	}

	static boolean cover(int x) {
		for (int y : canCover[x]) {
			if (used[y])
				continue;
			used[y] = true;
			if (covered[y] == 0 || cover(covered[y])) {
				covered[y] = x;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][];

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int total = n * m;
		int answer = total;

		canCover = new ArrayList[total + 1];
		used = new boolean[total + 1];
		covered = new int[total + 1];

		for (int i = 1; i <= total; i++) {
			canCover[i] = new ArrayList<>();
		}

		int[] dx = new int[] { 0, 0, 1, -1 };
		int[] dy = new int[] { 1, -1, 0, 0 };

		int nx, ny;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'X') {
					answer--;
					continue;
				} else if ((i + j) % 2 != 0)
					continue;

				for (int k = 0; k < 4; k++) {
					nx = i + dx[k];
					ny = j + dy[k];
					if (!check(nx, ny))
						continue;
					canCover[i * m + j + 1].add(nx * m + ny + 1);
				}
			}
		}

		for (int i = 1; i <= total; i++) {
			Arrays.fill(used, false);
			if (cover(i))
				answer--;
		}
        
		System.out.println(answer);
	}
}