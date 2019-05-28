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
			String message = "�ڷ�����";
			Button start;// ���尴ť
			Button explain;// ���尴ť
			Button regret;// ���尴ť
			Button defeat;// ���尴ť
			Button quit;// ���尴ť

			public FiveChessFrame() {
				this.setTitle("������");// ���ô������
				this.setSize(650, 650); // ���ô����
				this.setResizable(false);// �����С���ɸı�
				float color[] = Color.RGBtoHSB(119, 163, 153, new float[] { 119, 163,
						153 });// ����һ���Զ���ı�����ɫ 119 136 153//
				this.setBackground(Color.getHSBColor(color[0], color[1], color[2]));// ���ñ�����ɫ
				this.setLocation((width - 680) / 2, (height - 720) / 2);// �����ʼ��ʾλ��,����Ļ������
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �رմ���ʱͬʱ��������
				this.addMouseListener(this);// Ϊ������������
				setLayout(null);// ���ò���Ϊ��

				// ��ʼ����ʼ��ť
				start = new Button("��ʼ");// ��ť������
				start.setBounds(60, 60, 100, 50);// ��ť��λ�ü���С
				start.setFont(new Font("Default", Font.BOLD, 30));// ���ð�ť����Ĵ�С
				start.addMouseListener(this);// Ϊ��ť���������
				add(start);// ���밴ť

				// ��ʼ��˵����ť
				explain = new Button("˵��");
				explain.setBounds(170, 60, 100, 50);// ���ð�ť��λ�ü���С
				explain.setFont(new Font("Default", Font.BOLD, 30));// ���ð�ť����Ĵ�С
				explain.addMouseListener(this);// Ϊ��ť���������
				add(explain);

				regret = new Button("����");
				regret.setBounds(280, 60, 100, 50);
				regret.setFont(new Font("Default", Font.BOLD, 30));// ���ð�ť����Ĵ�С
				regret.addMouseListener(this);// Ϊ��ť���������
				add(regret);

				defeat = new Button("����");
				defeat.setBounds(390, 60, 100, 50);
				defeat.setFont(new Font("Default", Font.BOLD, 30));// ���ð�ť����Ĵ�С
				defeat.addMouseListener(this);
				add(defeat);

				quit = new Button("�˳�");
				quit.setBounds(500, 60, 100, 50);
				quit.setFont(new Font("Default", Font.BOLD, 30));// ���ð�ť����Ĵ�С
				quit.addMouseListener(this);
				add(quit);

				this.setVisible(true);// ������ʾ
			}

			public void paint(Graphics g) {
				g.setColor(Color.orange);// ������ɫ
				g.setFont(new Font("����", Font.BOLD, 45));// ��������
				g.drawString("��", 90, 70);// ��������
				g.drawString("��", 292, 70);// ��������
				g.drawString("��", 490, 70);// ��������

				// ��Ϸ��Ϣ��ʾ
				float color[] = Color.RGBtoHSB(119, 163, 153, new float[] { 119, 163, 153,
						});// ����һ���Զ���ı�����ɫ
				g.setColor(Color.getHSBColor(color[0], color[1], color[2]));// ���ñ�����ɫ
				g.fillRect(0, 145, 650, 640);// ��һ����ɫ��������ɫһ���ľ���,����֮ǰ������
				g.setColor(Color.black);
				g.setFont(new Font("����", Font.BOLD, 28));// �������Ӵ�30��
				g.drawString("��Ϸ��Ϣ:" + message, 60, 170);// ��������

				// ��������
				// ((Graphics2D) g).setStroke(new BasicStroke(1));
				g.setColor(Color.white);// �������̱�����ɫ
				g.fillRect(120, 180, 450, 450);// �������̱�����ɫ�Ĵ�С
				for (int i = 0; i < 19; i++) {
					g.setColor(Color.black);// �����ߵ���ɫ
					g.drawLine(120, 180 + 25 * i, 570, 180 + 25 * i);// �����̵ĺ���
					g.drawLine(120 + 25 * i, 180, 120 + 25 * i, 630);// �����̵�����
				}
				// ��ע��λ
				g.fillOval(192, 252, 6, 6);
				g.fillOval(192, 402, 6, 6);
				g.fillOval(192, 552, 6, 6);
				g.fillOval(342, 252, 6, 6);
				g.fillOval(342, 402, 6, 6);
				g.fillOval(342, 552, 6, 6);
				g.fillOval(492, 252, 6, 6);
				g.fillOval(492, 402, 6, 6);
				g.fillOval(492, 552, 6, 6);

				// ��������
				for (int i = 0; i < 19; i++) {
					for (int j = 0; j < 19; j++) {
						if (allchess[i][j] == 1) {
							// ���ƺ�ɫ����
							int temx = i * 25 + 20;
							int temy = j * 25 + 180;
							g.setColor(Color.black);
							g.fillOval(temx - 8, temy - 8, 16, 16);
						}
						if (allchess[i][j] == 2) {
							// ���ư�ɫ����
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
				// ʵ�ֿ�ʼ��ť
				if (e.getComponent().equals(start)) {
					int result = JOptionPane.showConfirmDialog(this, "�Ƿ�ȷ�����¿�ʼ��Ϸ��");
					if (result == 0) {// ���ѡ����,�����¿�ʼ��Ϸ
						for (int i = 0; i < LINE_COUNT; i++) {
							for (int j = 0; j < LINE_COUNT; j++) {
								allchess[i][j] = 0;// ���������,allChess���������ȫ�����ݹ�0.
							}
						}
						message = "�ڷ�����";// �� ��Ϸ��Ϣ: ����ʾ�Ļص���ʼλ��
						nowblack = true;// ����һ������ĸ�Ϊ�ڷ�
						canplay=true;//��ǰ��Ϸ���Լ���
						number = 0;//�Ѿ��µ���������Ϊ0
						last_x = last_y = -1;
						this.repaint();
					}

				}
				// ʵ��˵����ť
				else if (e.getComponent().equals(explain)) {
					JOptionPane.showMessageDialog(this,
							"���һ����������Ϸ���򣬺ڰ�˫���������壬�������£�\r\n"
							+ "���������̺��������б���γ�������ͬɫ������ӵ�\r\n"
							+ "һ��Ϊʤ����Ϸ������\r\n" );
				}

				// ʵ�ֻ��尴ť
				else if (e.getComponent().equals(regret)) {
					if (canplay) {
						if (JOptionPane.showConfirmDialog(this, "ȷ�ϻ���?") == 0) {
							if (last_x != -1 && last_y != -1) {
								allchess[last_x][last_y] = 0;
								repaint();
								last_x = last_y = -1;
								number--;// �������ѷ���������һ
							} else {
								JOptionPane.showMessageDialog(this, "ֻ�ܻ�һ����!");
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "��Ϸ�Ѿ�����,����ִ�л������");
					}
				}
				// ʵ�����䰴ť
				else if (e.getComponent().equals(defeat)) {
					if (canplay) {
						int result = JOptionPane.showConfirmDialog(this, "�Ƿ�ȷ������?");
						if (result == 0) {
							if (nowblack) {
								JOptionPane.showMessageDialog(this, "�ڷ����䣬�׷�ʤ������Ϸ����");
							} else {
								JOptionPane.showMessageDialog(this, "�׷����䣬�ڷ�ʤ������Ϸ����");
							}
						}
						canplay = false;
					} else {
						JOptionPane.showMessageDialog(this, "��Ϸ�Ѿ�����,����ִ���������");
					}
				}

				// ʵ���˳���ť
				else if (e.getComponent().equals(quit)) {
					int result=JOptionPane.showConfirmDialog(this, "��Ϸ�������Ƿ�ȷ���˳���");
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
						last_x = x = Math.round((x - 20) / 25f);//������̸����ĵ�,����������껭����
						last_y = y = Math.round((y - 180) / 25f);
						if (allchess[x][y] == 0) {
							// �жϵ�ǰҪ�µ���ʲô��ɫ������
							if (nowblack == true) {
								allchess[x][y] = 1;
								nowblack = false;
								message = "�ֵ��׷�";
							} else {
								allchess[x][y] = 2;
								nowblack = true;
								message = "�ֵ��ڷ�";
							}
							number++;//�����µ�������+1
							// �ж���Ϸ�Ƿ����
							if (win()) {
								JOptionPane.showMessageDialog(this, "��Ϸ����,"
										+ (allchess[x][y] == 1 ? "�ڷ�" : "�׷�") + "��ʤ��");
								canplay = false;
							}
							repaint();// ����paint����
						} 
						//�жϵ�ǰλ���Ƿ�������
						else {
							JOptionPane.showMessageDialog(this, "��ǰλ���������ӣ�����������");
						}
					}
					//�ж�ƽ��
					if (number >= 19*19) {
						JOptionPane.showMessageDialog(this, "��Ϸ����,˫��δ�ֳ�ʤ��!\n"
								+ "��Ҫ������Ϸ,�����¿�ʼ��");
						canplay = false;
					}
				} 
				if(canplay==false&&x >= 8 && x <= 482 && y >= 168 && y <= 642){
					JOptionPane.showMessageDialog(this, "��Ϸ�Ѿ�����,�������,�����¿�ʼ��Ϸ!");
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
				// ������жϣ���������ͬ
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
				// б������жϣ����Ϻ�����
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
				// б������жϣ����º�����
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
	


