import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main extends JFrame {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HIEGHT = 720;
	public static final int Drop_SPEED = 10;
	public Container cp = getContentPane();
	private Image screenImage;
	private Graphics screenGraphic;
	private Music introMusic = new Music("HamburgerSong.mp3", true);
	private Music BackgroundMusic = new Music("BackgroundMusic.mp3", true);
	private Music NowPlaying;
	private Font font = new Font("Bob's Burgers", Font.BOLD, 50);
	private ImageIcon exitButtonEnterImage = new ImageIcon(Main.class.getResource("images/exitB.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("images/exitA.png"));
	private ImageIcon HamburgerImage = new ImageIcon(Main.class.getResource("Images/HamburgerBasic.png"));
	private Image Background = new ImageIcon(Main.class.getResource("images/IntroBackground1280Basic.jpg")).getImage();
	private ImageIcon HamburgerEmotion = new ImageIcon(Main.class.getResource("images/Hamburger.png"));
	private ImageIcon PlayImage = new ImageIcon(Main.class.getResource("images/PlayButton.png"));
	private ImageIcon QuitImage = new ImageIcon(Main.class.getResource("images/QuitButton.png"));
	private ImageIcon PlayButtonEntered = new ImageIcon(Main.class.getResource("images/PlayButtonEnterd.png"));
	private ImageIcon QuitButtonEntered = new ImageIcon(Main.class.getResource("images/QuitButtonEnterd.png"));
	private ImageIcon BackPointimage = new ImageIcon(Main.class.getResource("images/BackPoint.png"));
	private ImageIcon BackPointEnterimage = new ImageIcon(Main.class.getResource("images/BackPointEntered.png"));



	private Image UpbunsIcon = new ImageIcon(Main.class.getResource("images/upBunsIcon.png")).getImage();
	private Image CheeseIcon = new ImageIcon(Main.class.getResource("images/CheeseIcon.png")).getImage();
	private Image PattyIcon = new ImageIcon(Main.class.getResource("images/PattyIcon.png")).getImage();
	private Image DownbunsIcon = new ImageIcon(Main.class.getResource("images/downBunsIcon.png")).getImage();
	private Image LettuceIcon = new ImageIcon(Main.class.getResource("images/LettuceIcon.png")).getImage();
	private Image TomatoIcon = new ImageIcon(Main.class.getResource("images/TomatoIcon.png")).getImage();

	private Image SelectedUpbuns = new ImageIcon(Main.class.getResource("images/SelectedupBuns.png")).getImage();
	private Image SelectedCheese = new ImageIcon(Main.class.getResource("images/SelectedCheese.png")).getImage();
	private Image SelectedPatty = new ImageIcon(Main.class.getResource("images/SelectedPatty.png")).getImage();
	private Image SelectedDownbuns = new ImageIcon(Main.class.getResource("images/SelecteddownBuns.png")).getImage();
	private Image SelectedLettuce = new ImageIcon(Main.class.getResource("images/SelectedLettuce.png")).getImage();
	private Image SelectedTomato = new ImageIcon(Main.class.getResource("images/SelectedTomato.png")).getImage();

	

	private Image Upbuns = new ImageIcon(Main.class.getResource("images/RealUpbuns.png")).getImage();
	private Image Cheese = new ImageIcon(Main.class.getResource("images/RealCheese.png")).getImage();
	private Image Patty = new ImageIcon(Main.class.getResource("images/Realpatty.png")).getImage();
	private Image Downbuns = new ImageIcon(Main.class.getResource("images/RealDownbuns.png")).getImage();
	private Image Lettuce = new ImageIcon(Main.class.getResource("images/RealLettuce.png")).getImage();
	private Image Tomato = new ImageIcon(Main.class.getResource("images/RealTomato.png")).getImage();

	private ImageIcon GameStartButtonImage = new ImageIcon(Main.class.getResource("images/GameStartButton.png"));
	private ImageIcon GameStartButtonEnterImage = new ImageIcon(
			Main.class.getResource("images/GameStartButtonEnterd.png"));
	private JLabel MenuBar = new JLabel(new ImageIcon(Main.class.getResource("images/menuBar.png")));
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton HamburgerButton = new JButton(HamburgerImage);
	private JButton PlayButton = new JButton(PlayImage);
	private JButton QuitButton = new JButton(QuitImage);
	private JButton BackPointButton = new JButton(BackPointimage);
	private JButton GameStartButton = new JButton(GameStartButtonImage);
	private int mouseX, mouseY;
	private boolean isMainscreen = false;
	private boolean ingredietnsBgScreen = false;
	
	private JLabel timerLabel = null;
	private JLabel secondLabel = null;
	private int sec = 60;

	private ImageIcon Selected = new ImageIcon(Main.class.getResource("images/Selected.png"));
	private JLabel SelectedIcon = new JLabel(Selected);

	ArrayList<List> ingredientslist = new ArrayList<List>();

	private int nowSelected = 0;
	ArrayList<Drop> dropList = new ArrayList<Drop>();
	private int cnt = 0;

	Main() {
		setUndecorated(true);// 기존 메뉴바 안보이게함
		setTitle("Stacking Hamburger");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HIEGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));// paintComponent 배경색 흰색으로
		setLayout(null);// 버튼,제이라벨 그위치 그대로 꽂히게함
		addKeyListener(new KeyListener());
		introMusic.start();

		ingredientslist.add(new List("SelectedupBuns.png", "CheeseIcon.png", "PattyIcon.png", "downBunsIcon.png",
				"LettuceIcon.png", "TomatoIcon.png"));
		ingredientslist.add(new List("upBunsIcon.png", "SelectedCheese.png", "PattyIcon.png", "downBunsIcon.png",
				"LettuceIcon.png", "TomatoIcon.png"));
		ingredientslist.add(new List("upBunsIcon.png", "CheeseIcon.png", "SelectedPatty.png", "downBunsIcon.png",
				"LettuceIcon.png", "TomatoIcon.png"));
		ingredientslist.add(new List("upBunsIcon.png", "CheeseIcon.png", "PattyIcon.png", "SelecteddownBuns.png",
				"LettuceIcon.png", "TomatoIcon.png"));
		ingredientslist.add(new List("upBunsIcon.png", "CheeseIcon.png", "PattyIcon.png", "downBunsIcon.png",
				"SelectedLettuce.png", "TomatoIcon.png"));
		ingredientslist.add(new List("upBunsIcon.png", "CheeseIcon.png", "PattyIcon.png", "downBunsIcon.png",
				"LettuceIcon.png", "SelectedTomato.png"));

		if (isMainscreen == true) {
			BackgroundMusic.start();
		}
		HamburgerButton.setBounds(495, 222, 295, 285);
		HamburgerButton.setBorderPainted(false);
		;
		HamburgerButton.setContentAreaFilled(false);
		HamburgerButton.setFocusPainted(false);
		HamburgerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				HamburgerButton.setIcon(HamburgerEmotion);
				HamburgerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				HamburgerButton.setIcon(HamburgerImage);
				HamburgerButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				ButtonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

				EnterMain();
				// 게임시작이벤트

			}
		});
		add(HamburgerButton);

		exitButton.setBounds(1245, 0, 30, 30);

		exitButton.setBorderPainted(false);
		;
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnterImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				ButtonPressedMusic.start();
				try {
					Thread.sleep(1000);// 1초 딜레이
				} catch (InterruptedException ex) {
					ex.printStackTrace();

				}
				System.exit(0);
			}
		});
		add(exitButton);
		PlayButton.setBounds(1000, 450, 210, 110);
		PlayButton.setBorderPainted(false);
		PlayButton.setContentAreaFilled(false);
		PlayButton.setFocusPainted(false);
		PlayButton.setVisible(false);
		PlayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				PlayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				PlayButton.setIcon(PlayButtonEntered);
				Music GrillingSound = new Music("SteakGrilling.mp3", false);
				GrillingSound.start();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				PlayButton.setIcon(PlayImage);
				HamburgerButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				ButtonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				gameStart();
				selectList(0);
			}
		});
		add(PlayButton);
		QuitButton.setBounds(1000, 550, 210, 110);
		QuitButton.setBorderPainted(false);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setFocusPainted(false);
		QuitButton.setVisible(false);
		QuitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				QuitButton.setIcon(QuitButtonEntered);
				QuitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music GrillingSound = new Music("SteakGrilling.mp3", false);
				GrillingSound.start();

			}

			@Override
			public void mouseExited(MouseEvent e) {

				QuitButton.setIcon(QuitImage);
				QuitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {

				Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				ButtonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(QuitButton);
		BackPointButton.setVisible(false);
		BackPointButton.setBounds(10, 660, 110, 60);
		BackPointButton.setBorderPainted(false);
		;
		BackPointButton.setContentAreaFilled(false);
		BackPointButton.setFocusPainted(false);
		BackPointButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				BackPointButton.setIcon(BackPointEnterimage);
				BackPointButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				BackPointButton.setIcon(BackPointimage);
				BackPointButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				ButtonPressedMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				// 게임시작이벤트
				BackMain();

			}
		});
		add(BackPointButton);
		MenuBar.setBounds(0, 0, 1280, 30);
		MenuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		MenuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(MenuBar);
		GameStartButton.setBounds(990, 215, 218, 103);
		GameStartButton.setBorderPainted(false);
		GameStartButton.setContentAreaFilled(false);
		GameStartButton.setFocusPainted(false);
		GameStartButton.setVisible(false);
		GameStartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				GameStartButton.setIcon(GameStartButtonEnterImage);
				GameStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				GameStartButton.setIcon(GameStartButtonImage);
				GameStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
				ButtonPressedMusic.start();
				try {
					Thread.sleep(1000);// 1초 딜레이
				} catch (InterruptedException ex) {
					ex.printStackTrace();

				}
				GameStartButton.setVisible(false);
				(new framesecThread(secondLabel)).start();
				(new secThread(timerLabel)).start();
			}
		});
		add(GameStartButton);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HIEGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(Background, 0, 0, null);
		paintComponents(g);
		if (ingredietnsBgScreen) {

			g.drawImage(UpbunsIcon, 42, 150, null);
			g.drawImage(CheeseIcon, 152, 150, null);
			g.drawImage(PattyIcon, 262, 150, null);
			g.drawImage(DownbunsIcon, 42, 262, null);
			g.drawImage(LettuceIcon, 152, 262, null);
			g.drawImage(TomatoIcon, 262, 262, null);

			g.drawImage(Upbuns, 450, -300, null);// 450,0
			g.drawImage(Cheese, 430, -300, null);// 430,120
			g.drawImage(Patty, 420, -300, null);// 420,100
			g.drawImage(Downbuns, 450, -300, null);// 450,0
			g.drawImage(Lettuce, 450, -300, null);// 450,100
			g.drawImage(Tomato, 450, -300, null);// 450,140
			for (int i = 0; i < dropList.size(); i++) {
				Drop drop = dropList.get(i);
				drop.screenDraw(g);
			}

		}
		this.repaint();

	}



	public void Droping() {
		if (nowSelected == 0) {
			Drop drop = new Drop(450, -300, "Upbuns");
			drop.start();
			dropList.add(drop);
		}
		if (nowSelected == 1) {
			Drop drop = new Drop(440, -180, "Cheese");
			drop.start();
			dropList.add(drop);
		}
		if (nowSelected == 2) {
			Drop drop = new Drop(420, -200, "Patty");
			drop.start();
			dropList.add(drop);
		}

		if (nowSelected == 3) {
			Drop drop = new Drop(450, -300, "Downbuns");
			drop.start();
			dropList.add(drop);
		}

		if (nowSelected == 4) {
			Drop drop = new Drop(450, -200, "Lettuce");
			drop.start();
			dropList.add(drop);
		}

		if (nowSelected == 5) {
			Drop drop = new Drop(450, -160, "Tomato");
			drop.start();
			dropList.add(drop);
		}


	}

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = 0;
		} else if (nowSelected == 3) {
			nowSelected = 3;
		} else
			nowSelected--;
		selectList(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == 2) {
			nowSelected = 2;
		} else if (nowSelected == 5) {
			nowSelected = 5;
		} else
			nowSelected++;
		selectList(nowSelected);
	}

	public void selectDown() {
		if (nowSelected == 3) {
			nowSelected = 3;
		} else if (nowSelected == 4) {
			nowSelected = 4;
		} else if (nowSelected == 5) {
			nowSelected = 5;
		} else if (nowSelected == 0) {
			nowSelected = 3;
		} else if (nowSelected == 1) {
			nowSelected = 4;
		} else if (nowSelected == 2) {
			nowSelected = 5;
		}
		selectList(nowSelected);
	}

	public void selectUp() {
		if (nowSelected == 0) {
			nowSelected = 0;
		} else if (nowSelected == 1) {
			nowSelected = 1;
		} else if (nowSelected == 2) {
			nowSelected = 2;
		} else if (nowSelected == 3) {
			nowSelected = 0;
		} else if (nowSelected == 4) {
			nowSelected = 1;
		} else if (nowSelected == 5) {
			nowSelected = 2;
		}
		selectList(nowSelected);
	}

	public void selectList(int nowSelected) {
		UpbunsIcon = new ImageIcon(
				Main.class.getResource("images/" + ingredientslist.get(nowSelected).getSelectimage())).getImage();
		CheeseIcon = new ImageIcon(
				Main.class.getResource("images/" + ingredientslist.get(nowSelected).getUnsselectimage1())).getImage();
		PattyIcon = new ImageIcon(
				Main.class.getResource("images/" + ingredientslist.get(nowSelected).getUnsselectimage2())).getImage();
		DownbunsIcon = new ImageIcon(
				Main.class.getResource("images/" + ingredientslist.get(nowSelected).getUnsselectimage3())).getImage();
		LettuceIcon = new ImageIcon(
				Main.class.getResource("images/" + ingredientslist.get(nowSelected).getUnsselectimage4())).getImage();
		TomatoIcon = new ImageIcon(
				Main.class.getResource("images/" + ingredientslist.get(nowSelected).getUnsselectimage5())).getImage();

	}



	public void gameStart() {// play버튼 게임시작내부
		isMainscreen = false;
		ingredietnsBgScreen = true;
		GameStartButton.setVisible(true);
		NowPlaying.close();
		Background = new ImageIcon(Main.class.getResource("images/PlayBackground2.png")).getImage();
		PlayButton.setVisible(false);
		QuitButton.setVisible(false);
		BackPointButton.setVisible(true);

		NowPlaying = new Music("GamePlayMusic.mp3", true);
		NowPlaying.start();
		

		timerLabel = new JLabel("00");
		timerLabel.setFont(font);
		timerLabel.setBounds(1080, 50, 90, 90);
		timerLabel.setForeground(Color.YELLOW);

		secondLabel = new JLabel(":00");
		secondLabel.setFont(font);
		secondLabel.setBounds(1135, 50, 90, 90);
		secondLabel.setForeground(Color.YELLOW);

		add(timerLabel);
		add(secondLabel);
		setFont(font);
		setFocusable(true);
		requestFocus();
	}

	public void BackMain() {// playbutton, quitbutton 인트로2
		isMainscreen = true;
		ingredietnsBgScreen = false;
		NowPlaying.close();
		Background = new ImageIcon(Main.class.getResource("images/BackgroundTitle1.jpg")).getImage();
		PlayButton.setVisible(true);
		QuitButton.setVisible(true);
		BackPointButton.setVisible(false);

		GameStartButton.setVisible(false);
		timerLabel.setVisible(false);
		secondLabel.setVisible(false);
		SelectedIcon.setVisible(false);
		NowPlaying.close();
		NowPlaying = new Music("BackgroundMusic.mp3", true);
		NowPlaying.start();
	}

	public void EnterMain() {// 인트로 화면1
		isMainscreen = true;
		ingredietnsBgScreen = false;
		SelectedIcon.setVisible(false);
		introMusic.close();
		GameStartButton.setVisible(false);
		Background = new ImageIcon(Main.class.getResource("images/BackgroundTitle1.jpg")).getImage();
		HamburgerButton.setVisible(false);
		PlayButton.setVisible(true);
		QuitButton.setVisible(true);

		NowPlaying = new Music("BackgroundMusic.mp3", true);
		NowPlaying.start();
	}

	public class secThread extends Thread {

		JLabel myLabel = null;

		public secThread(JLabel myLabel) {
			this.myLabel = myLabel;
		}

		@Override
		public void run() {
			while (true) {
				if (sec < 10) {
					myLabel.setText("0" + sec);
				}
				myLabel.setText("" + sec);
				if (sec == 0)
					return;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sec--;
			}
		}
	}

	public class framesecThread extends Thread {
		int frame = 100;
		JLabel myLabel = null;

		public framesecThread(JLabel myLabel) {
			this.myLabel = myLabel;
		}

		@Override
		public void run() {
			while (true) {
				if (frame < 10 && frame >= 0)
					myLabel.setText(":0" + frame);
				myLabel.setText(":" + frame);

				if (sec == 0 && frame == 0)
					return;// 초가 0되면ㅇ ㅣ것도 끝내야댐
				if (frame == 0)
					frame = 100;
				try {
					Thread.sleep(9);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame--;
			}
		}
	}

	public class KeyListener extends KeyAdapter {
		@Override

		public void keyPressed(KeyEvent e) {
			// if(Main.game==null) {return;}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				selectLeft();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				selectRight();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				selectDown();
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				selectUp();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {

				if (cnt < 15)
					Droping();
				cnt++;
				if (cnt == 16) { repaint();
					
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				GameStartButton.setVisible(false);
				(new framesecThread(secondLabel)).start();
				(new secThread(timerLabel)).start();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {

			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			}
		}
	}

	public class Drop extends Thread {

		private int x, y;
		private String Droptype;

		private int mm = 30;
		private int end = cnt * mm;

		public Drop(int x, int y, String Droptype) {

			this.x = x;
			this.y = y;
			this.Droptype = Droptype;

		}

		public void screenDraw(Graphics2D g) {
			paintComponents(g);
			if (Droptype.equals("Upbuns")) {
				g.drawImage(Upbuns, x, y, null);
			} else if (Droptype.equals("Cheese")) {
				g.drawImage(Cheese, x, y, null);
			} else if (Droptype.equals("Patty")) {
				g.drawImage(Patty, x, y, null);
			} else if (Droptype.equals("Downbuns")) {
				g.drawImage(Downbuns, x, y, null);
			} else if (Droptype.equals("Lettuce")) {
				g.drawImage(Lettuce, x, y, null);
			} else if (Droptype.equals("Tomato")) {
				g.drawImage(Tomato, x, y, null);
			}
		
		}
		public void update(Graphics2D g) {
			paintComponents(g);
			g.drawImage(Upbuns, 450, -300, null);// 450,0
			g.drawImage(Cheese, 430, -300, null);// 430,120
			g.drawImage(Patty, 420, -300, null);// 420,100
			g.drawImage(Downbuns, 450, -300, null);// 450,0
			g.drawImage(Lettuce, 450, -300, null);// 450,100
			g.drawImage(Tomato, 450, -300, null);// 450,140
		}
		
	
		public void drop() {
			y += Main.Drop_SPEED;
		}

		@Override
		public void run() {
			try {

				while (true) {

					drop();
					Thread.sleep(10);
					
					System.out.println(cnt);
					if (Droptype.equals("Cheese")) {
						if (y == 480 - end) {
							break;
						}
					} else if (Droptype.equals("Patty")) {
						if (y == 450 - end) {
							break;
						}
					} else if (Droptype.equals("Upbuns")) {
						if (y == 330 - end) {
							break;
						}
					} else if (Droptype.equals("Downbuns")) {
						if (y == 350 - end) {
							break;
						}
					} else if (Droptype.equals("Lettuce")) {
						if (y == 420 - end) {
							break;
						}
					} else if (Droptype.equals("Tomato")) {
						if (y == 480 - end) {
							break;
						}
					}

				}

			} catch (Exception e) {
				repaint();
				System.err.println(e.getMessage());
			}
			
		}
	}

	public static void main(String[] args) {

		new Main();
	}

}
