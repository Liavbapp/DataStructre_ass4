//This function prints your tree in a treeish form. if two nodes has a ___ between them it means they are not from the same parent.
public void PrintTree() {
		String strTree = toString();
		String[] treeLevels = strTree.split("#");
		for (int i = 0; i < treeLevels.length; i++) {
			String[] level = treeLevels[i].split("\\|");
			for (int j = 0; j < Math.pow(2, treeLevels.length - i); j++)
				System.out.print("  ");
			for (int k = 0; k < level.length; k++) {
				if (level[k].contains("^")) {
					String[] level2 = level[k].split("\\^");
					System.out.print("[" + level2[0] + "]");
					for (int j = 0; j < Math.pow(3, treeLevels.length - i); j++)
						System.out.print("_");
					System.out.print("[" + level2[1] + "]");
				} else
					System.out.print("[" + level[k] + "]");
				for (int j = 0; j < Math.pow(2, treeLevels.length - i); j++)
					System.out.print(" ");
			}
			System.out.println();
		}
	}