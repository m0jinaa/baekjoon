import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	static StringBuilder sb;
	static int[] dx;
	static int m;
	static int[] before;

	static void track(int x) {
		if (x == 0)
			return;
		if (before[x] == -1) {
			sb.append(before[x]);
			return;
		}
		track(before[x]);
		sb.append(x - before[x]).append(" ");
	}

	static void getNum(int x) {
		LinkedList<Integer> q = new LinkedList<>();

		q.add(0);
		int now, next;
		while (!q.isEmpty()) {
			now = q.poll();

			if (now == x)
				break;
			for (int i = 0; i < m; i++) {
				next = now + dx[i];
				if (next > x || before[next] != -1)
					continue;

				before[next] = now;
				q.add(next);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		dx = new int[] { 4, 7, 44, 47, 74, 77, 444, 447, 474, 477, 744, 747, 774, 777, 4444, 4447, 4474, 4477, 4744,
				4747, 4774, 4777, 7444, 7447, 7474, 7477, 7744, 7747, 7774, 7777, 44444, 44447, 44474, 44477, 44744,
				44747, 44774, 44777, 47444, 47447, 47474, 47477, 47744, 47747, 47774, 47777, 74444, 74447, 74474, 74477,
				74744, 74747, 74774, 74777, 77444, 77447, 77474, 77477, 77744, 77747, 77774, 77777, 444444, 444447,
				444474, 444477, 444744, 444747, 444774, 444777, 447444, 447447, 447474, 447477, 447744, 447747, 447774,
				447777, 474444, 474447, 474474, 474477, 474744, 474747, 474774, 474777, 477444, 477447, 477474, 477477,
				477744, 477747, 477774, 477777, 744444, 744447, 744474, 744477, 744744, 744747, 744774, 744777, 747444,
				747447, 747474, 747477, 747744, 747747, 747774, 747777, 774444, 774447, 774474, 774477, 774744, 774747,
				774774, 774777, 777444, 777447, 777474, 777477, 777744, 777747, 777774, 777777 };
		m = dx.length;

		int n = Integer.parseInt(br.readLine());
		before = new int[n + 1];
		Arrays.fill(before, -1);
		getNum(n);

		track(n);

		System.out.println(sb.toString());

	}
}