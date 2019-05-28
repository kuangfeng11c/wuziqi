import java.awt.Button;
		import java.awt.*;
		import java.awt.event.*;
		import javax.swing.JFrame;
		import javax.swing.JOptionPane;

		class FiveChessFrame extends JFrame implements MouseListener {

			final static int LINE_COUNT = 19;
			int width = Toolkit.getDefaultToolkit().getScreenSize().width;
			int height = Toolkit.getDefaultToolkit().getScreenSize().height;
			int x = 0, y = 0;
			int[][] allchess = new int[19][19];
			int last_x = -1, last_y = -1;
			int number = 0;
			boolean nowblack = true;
			boolean canplay = true;
			String message = "黑方先行";
			Button start;// 定义按钮
			Button explain;// 定义按钮
			Button regret;// 定义按钮
			Button defeat;// 定义按钮
			Button quit;// 定义按钮

			public FiveChessFrame() {
				this.setTitle("五子棋");// 设置窗体标题
				this.setSize(650, 650); // 设置窗体大
				this.setResizable(false);// 窗体大小不可改变
				float color[] = Color.RGBtoHSB(119, 163, 153, new float[] { 119, 163,
						153 });// 生成一个自定义的背景颜色 119 136 153//
				this.setBackground(Color.getHSBColor(color[0], color[1], color[2]));// 设置背景颜色
				this.setLocation((width - 680) / 2, (height - 720) / 2);// 窗体初始显示位置,在屏幕正中央
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗体时同时结束进程
				this.addMouseListener(this);// 为窗体加入监听器
				setLayout(null);// 设置布局为空

				// 初始化开始按钮
				start = new Button("开始");// 按钮的名称
				start.setBounds(60, 60, 100, 50);// 按钮的位置及大小
				start.setFont(new Font("Default", Font.BOLD, 30));// 设置按钮字体的大小
				start.addMouseListener(this);// 为按钮加入监听器
				add(start);// 加入按钮

				// 初始化说明按钮
				explain = new Button("说明");
				explain.setBounds(170, 60, 100, 50);// 设置按钮的位置及大小
				explain.setFont(new Font("Default", Font.BOLD, 30));// 设置按钮字体的大小
				explain.addMouseListener(this);// 为按钮加入监听器
				add(explain);

				regret = new Button("悔棋");
				regret.setBounds(280, 60, 100, 50);
				regret.setFont(new Font("Default", Font.BOLD, 30));// 设置按钮字体的大小
				regret.addMouseListener(this);// 为按钮加入监听器
				add(regret);

				defeat = new Button("认输");
				defeat.setBounds(390, 60, 100, 50);
				defeat.setFont(new Font("Default", Font.BOLD, 30));// 设置按钮字体的大小
				defeat.addMouseListener(this);
				add(defeat);

				quit = new Button("退出");
				quit.setBounds(500, 60, 100, 50);
				quit.setFont(new Font("Default", Font.BOLD, 30));// 设置按钮字体的大小
				quit.addMouseListener(this);
				add(quit);

				this.setVisible(true);// 窗体显示
			}

			public void paint(Graphics g) {
				g.setColor(Color.orange);// 设置颜色
				g.setFont(new Font("宋体", Font.BOLD, 45));// 设置字体
				g.drawString("五", 90, 70);// 输入字体
				g.drawString("子", 292, 70);// 输入字体
				g.drawString("棋", 490, 70);// 输入字体

				// 游戏信息显示
				float color[] = Color.RGBtoHSB(119, 163, 153, new float[] { 119, 163, 153,
						});// 生成一个自定义的背景颜色
				g.setColor(Color.getHSBColor(color[0], color[1], color[2]));// 设置背景颜色
				g.fillRect(0, 145, 650, 640);// 画一个颜色跟背景颜色一样的矩形,覆盖之前的文字
				g.setColor(Color.black);
				g.setFont(new Font("黑体", Font.BOLD, 28));// 字体黑体加粗30号
				g.drawString("游戏信息:" + message, 60, 170);// 输入字体

				// 绘制棋盘
				// ((Graphics2D) g).setStroke(new BasicStroke(1));
				g.setColor(Color.white);// 绘制棋盘背景颜色
				g.fillRect(120, 180, 450, 450);// 绘制棋盘背景颜色的大小
				for (int i = 0; i < 19; i++) {
					g.setColor(Color.black);// 棋盘线的颜色
					g.drawLine(120, 180 + 25 * i, 570, 180 + 25 * i);// 画棋盘的横线
					g.drawLine(120 + 25 * i, 180, 120 + 25 * i, 630);// 画棋盘的竖线
				}
				// 标注点位
				g.fillOval(192, 252, 6, 6);
				g.fillOval(192, 402, 6, 6);
				g.fillOval(192, 552, 6, 6);
				g.fillOval(342, 252, 6, 6);
				g.fillOval(342, 402, 6, 6);
				g.fillOval(342, 552, 6, 6);
				g.fillOval(492, 252, 6, 6);
				g.fillOval(492, 402, 6, 6);
				g.fillOval(492, 552, 6, 6);

				// 绘制棋子
				for (int i = 0; i < 19; i++) {
					for (int j = 0; j < 19; j++) {
						if (allchess[i][j] == 1) {
							// 绘制黑色棋子
							int temx = i * 25 + 20;
							int temy = j * 25 + 180;
							g.setColor(Color.black);
							g.fillOval(temx - 8, temy - 8, 16, 16);
						}
						if (allchess[i][j] == 2) {
							// 绘制白色棋子
							int temx = i * 25 + 20;
							int temy = j * 25 + 180;
							g.setColor(Color.white);
							g.fillOval(temx - 8, temy - 8, 16, 16);
							g.setColor(Color.black);
							g.drawOval(temx - 8, temy - 8, 16, 16);
						}

					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// 实现开始按钮
				if (e.getComponent().equals(start)) {
					int result = JOptionPane.showConfirmDialog(this, "是否确认重新开始游戏？");
					if (result == 0) {// 如果选择是,则重新开始游戏
						for (int i = 0; i < LINE_COUNT; i++) {
							for (int j = 0; j < LINE_COUNT; j++) {
								allchess[i][j] = 0;// 把棋盘清空,allChess这个数组中全部数据归0.
							}
						}
						message = "黑方先行";// 将 游戏信息: 的显示改回到开始位置
						nowblack = true;// 将下一步下棋的改为黑方
						canplay=true;//当前游戏可以继续
						number = 0;//已经下的棋子数置为0
						last_x = last_y = -1;
						this.repaint();
					}

				}
				// 实现说明按钮
				else if (e.getComponent().equals(explain)) {
					JOptionPane.showMessageDialog(this,
							"这个一个五子棋游戏程序，黑白双方轮流下棋，黑棋先下；\r\n"
							+ "最先在棋盘横向、纵向或斜向形成连续的同色五个棋子的\r\n"
							+ "一方为胜，游戏结束。\r\n" );
				}

				// 实现悔棋按钮
				else if (e.getComponent().equals(regret)) {
					if (canplay) {
						if (JOptionPane.showConfirmDialog(this, "确认悔棋?") == 0) {
							if (last_x != -1 && last_y != -1) {
								allchess[last_x][last_y] = 0;
								repaint();
								last_x = last_y = -1;
								number--;// 悔棋则已放棋子数减一
							} else {
								JOptionPane.showMessageDialog(this, "只能悔一步棋!");
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "游戏已经结束,不能执行悔棋操作");
					}
				}
				// 实现认输按钮
				else if (e.getComponent().equals(defeat)) {
					if (canplay) {
						int result = JOptionPane.showConfirmDialog(this, "是否确定认输?");
						if (result == 0) {
							if (nowblack) {
								JOptionPane.showMessageDialog(this, "黑方认输，白方胜利，游戏结束");
							} else {
								JOptionPane.showMessageDialog(this, "白方认输，黑方胜利，游戏结束");
							}
						}
						canplay = false;
					} else {
						JOptionPane.showMessageDialog(this, "游戏已经结束,不能执行认输操作");
					}
				}

				// 实现退出按钮
				else if (e.getComponent().equals(quit)) {
					int result=JOptionPane.showConfirmDialog(this, "游戏结束，是否确定退出？");
					if (result==0) {
					System.exit(0);
					}
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				if (canplay) {
					if (x >= 8 && x <= 482 && y >= 168 && y <= 642) {
						last_x = x = Math.round((x - 20) / 25f);//点击棋盘附近的点,在最近的坐标画棋子
						last_y = y = Math.round((y - 180) / 25f);
						if (allchess[x][y] == 0) {
							// 判断当前要下的是什么颜色的棋子
							if (nowblack == true) {
								allchess[x][y] = 1;
								nowblack = false;
								message = "轮到白方";
							} else {
								allchess[x][y] = 2;
								nowblack = true;
								message = "轮到黑方";
							}
							number++;//已下下的棋子数+1
							// 判断游戏是否结束
							if (win()) {
								JOptionPane.showMessageDialog(this, "游戏结束,"
										+ (allchess[x][y] == 1 ? "黑方" : "白方") + "获胜！");
								canplay = false;
							}
							repaint();// 调用paint方法
						} 
						//判断当前位置是否有棋子
						else {
							JOptionPane.showMessageDialog(this, "当前位置已有棋子，请重新落子");
						}
					}
					//判断平局
					if (number >= 19*19) {
						JOptionPane.showMessageDialog(this, "游戏结束,双方未分出胜负!\n"
								+ "若要继续游戏,请重新开始！");
						canplay = false;
					}
				} 
				if(canplay==false&&x >= 8 && x <= 482 && y >= 168 && y <= 642){
					JOptionPane.showMessageDialog(this, "游戏已经结束,如想继续,请重新开始游戏!");
			}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			private boolean win() {
				boolean flag = false;
				int num = 1;
				
				int color = allchess[x][y];
				
				int i1 = 1;
				while (x + i1 <= 18 && color == allchess[x + i1][y]) {
					num++;
					i1++;
				}
				i1 = 1;
				while (x - i1 >= 0 && color == allchess[x - i1][y]) {
					num++;
					i1++;
				}
				if (num >= 5) {
					flag = true;
				}
				// 纵向的判断，横坐标相同
				int i2 = 1;
				int num2 = 1;
				while (y + i2 <= 18 && color == allchess[x][y + i2]) {
					num2++;
					i2++;
				}
				i2 = 1;
				while (y - i2 >= 0 && color == allchess[x][y - i2]) {
					num2++;
					i2++;
				}
				if (num2 >= 5) {
					flag = true;
				}
				// 斜方向的判断，右上和左下
				int i3 = 1;
				int num3 = 1;
				while (x + i3 <= 18 && y - i3 >= 0 && color == allchess[x + i3][y - i3]) {
					num3++;
					i3++;
				}
				i3 = 1;
				while (x - i3 >= 0 && y + i3 <= 18 && color == allchess[x - i3][y + i3]) {
					num3++;
					i3++;
				}
				if (num3 >= 5) {
					flag = true;
				}
				// 斜方向的判断，右下和左上
				int i4 = 1;
				int num4 = 1;
				while (x + i4 <= 18 && y + i4 <= 18
						&& color == allchess[x + i4][y + i4]) {
					num4++;
					i4++;
				}
				i4 = 1;
				while (x - i4 >= 0 && y - i4 >= 0 && color == allchess[x - i4][y - i4]) {
					num4++;
					i4++;
				}
				if (num4 >= 5) {
					flag = true;
				}
				return flag;
			}

		}
	


