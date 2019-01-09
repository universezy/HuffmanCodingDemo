import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Demo {
	private List<Node> nodes = new LinkedList<>();
	private Map<Character, String> encodes = new HashMap<>();

	// 设置权重
	private void setWeight(String input) {
		char[] chars = input.toCharArray();
		Map<Character, Integer> weightMap = new LinkedHashMap<>();
		for (int i = 0; i < chars.length; i++) {
			Integer integer = weightMap.get(chars[i]);
			weightMap.put(chars[i], integer == null ? 1 : integer + 1);
		}
		for (Entry<Character, Integer> entry : weightMap.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		nodes.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});
		System.out.println("=========== Set Weight ===========");
		for (int i = 0; i < nodes.size(); i++) {
			System.out.println("char:" + nodes.get(i).value + ", weight:" + nodes.get(i).weight);
		}
	}

	// 构造哈弗曼树
	private Node buildHuffmanTree() {
		Node root = nodes.get(0);
		for (int i = 1; i < nodes.size(); i++) {
			root = appendNode(root, nodes.get(i));
		}
		return root;
	}

	private Node appendNode(Node child1, Node child2) {
		Node parent = new Node(null, child1.weight + child2.weight);
		if (child1.weight < child2.weight) {
			parent.left = child2;
			parent.right = child1;
		} else {
			parent.left = child1;
			parent.right = child2;
		}
		child1.parent = parent;
		child2.parent = parent;
		parent.left.code = "0";
		parent.right.code = "1";
		return parent;
	}

	// 加密文本
	public String encode(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			sb.append(encodes.get(input.charAt(i)));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String input = "This is a test paragraph.";
		Demo demo = new Demo();
		demo.setWeight(input);
		Node root = demo.buildHuffmanTree();
		System.out.println("=========== Print Node ===========");
		root.encode();
		System.out.println("=========== Encode Text ===========");
		System.out.println("Input:" + input);
		System.out.println("Encode:" + demo.encode(input));
	}

	private class Node {
		private Character value;
		private int weight;
		private String code = "";
		private Node parent, left, right;

		public Node(Character value, int weight) {
			this.value = value;
			this.weight = weight;
		}

		public String getCode() {
			return parent == null ? code : parent.getCode() + code;
		}

		// 先序遍历
		public void encode() {
			if (value != null) {
				String encode = getCode();
				System.out.println("value=" + value + ", encode=" + encode);
				encodes.put(value, encode);
			}
			if (left != null)
				left.encode();
			if (right != null)
				right.encode();
		}
	}
}
