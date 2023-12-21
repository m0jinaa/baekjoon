import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	static int n = 10;
	static char[][] map;
	static boolean[][] v;

	static int[] dx = new int[] { 0, 0, 1, -1 };
	static int[] dy = new int[] { 1, -1, 0, 0 };

	static class Node {
		int x, y, d;

		public Node(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static boolean inRange(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= n)
			return false;
		return true;
	}

	static int bfs(int x, int y) {
		LinkedList<Node> q = new LinkedList<>();

		q.add(new Node(x, y, -1));
		v[x][y] = true;

		Node now;
		int nx, ny;
		int answer = -1;

		while (!q.isEmpty()) {
			now = q.poll();

			if (map[now.x][now.y] == 'L') {
				answer = now.d;
				break;
			}

			for (int i = 0; i < 4; i++) {
				nx = now.x + dx[i];
				ny = now.y + dy[i];

				if (!inRange(nx, ny) || map[nx][ny] == 'R' || v[nx][ny])
					continue;
				v[nx][ny] = true;
				q.add(new Node(nx, ny, now.d + 1));
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[n][];
		v = new boolean[n][n];

		int sx = -1, sy = -1;

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'B') {
					sx = i;
					sy = j;
				}
			}
		}

		int answer = bfs(sx, sy);

		System.out.println(answer);
	}
}