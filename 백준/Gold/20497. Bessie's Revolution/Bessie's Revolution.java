import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	static int n;
	static int[] dx = new int[] { 0, 0, 1, -1 };
	static int[] dy = new int[] { 1, -1, 0, 0 };
	static char[][] map;
	static boolean[][] v;
	static LinkedList<Node> q;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static boolean inRange(int x, int y) {
		return !(x < 0 || x >= n || y < 0 || y >= n);
	}

	static void bfs(int x, int y) {
		q.clear();

		q.add(new Node(x, y));
		v[x][y] = true;

		Node now;
		int nx, ny;

		while (!q.isEmpty()) {
			now = q.poll();

			for (int i = 0; i < 4; i++) {
				nx = now.x + dx[i];
				ny = now.y + dy[i];

				if (!inRange(nx, ny) || map[nx][ny] != '.' || v[nx][ny])
					continue;

				q.add(new Node(nx, ny));
				v[nx][ny] = true;
			}
		}
	}

	static int check() {

		for (int i = 0; i < n; i++) {
			Arrays.fill(v[i], false);
		}
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != '.' || v[i][j])
					continue;
				bfs(i, j);
				cnt++;
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		map = new char[n][];
		v = new boolean[n][n];
		q = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int original = check();

		int answer = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != '.')
					continue;

                //잠복하는 장소 체크
				map[i][j] = 'O';
                
				if (check() > original) {
					answer++;
				}
				map[i][j] = '.';
			}
		}

		System.out.println(answer);
	}
}