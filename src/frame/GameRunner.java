package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import hangman.Hangman;
import util.custom.ExistingEntryException;
import util.custom.ListEnhancer;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class GameRunner extends JFrame implements ActionListener, KeyListener, WindowListener
{
	private Hangman game;
	
	private JPanel paneGame;
	private JPanel paneWelcome;
	
	private JButton btnNewPlr;
	private JButton btnReturn;
	private JButton btnLoad;
	private JButton btnScoreboard;
	
	private JLabel lblHangman;
	
	private JTextField fldGuess;
	private JButton btnGuess;
	private JLabel lblWord;
	private JLabel lblGuessedWord;
	private JLabel lblGuessed;
	private JLabel lblGuessedLetters;
	
	//Menu
	private JMenuBar menuBar;
	private JMenu mnOptions;
	private JMenuItem mntmHint;
	private JMenuItem mntmRules;
	private JMenuItem mntmScoreboard;
	private JMenuItem mntmNew;
	private JMenuItem mntmQuit;
	private JMenuItem mntmClose;
	
	public GameRunner()
	{
		game = new Hangman();
		this.init();
	}
	
	private void init()
	{
		setTitle("Hangman");
		setBounds(100, 100, 400, 532);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		setResizable(false);
		getContentPane().setLayout(null);
		
		paneGame = new JPanel();
		paneGame.setBounds(0, 0, 400, 540);
		paneGame.setLayout(null);
		paneGame.setForeground(new Color(50, 205, 50));
		paneGame.setBackground(new Color(128, 0, 128));
		getContentPane().add(paneGame);
		
		paneWelcome = new JPanel();
		paneWelcome.setForeground(new Color(50, 205, 50));
		paneWelcome.setBackground(new Color(128, 0, 128));
		paneWelcome.setBounds(0, 0, 400, 267);
		paneWelcome.setLayout(null);
		getContentPane().add(paneWelcome);

		lblHangman = new JLabel();
		lblHangman.setBackground(new Color(128, 0, 128));
		lblHangman.setBounds(0, 21, 400, 400);
		paneGame.add(lblHangman);
		
		fldGuess = new JTextField();
		fldGuess.setBorder(new LineBorder(new Color(0, 128, 0)));
		fldGuess.setForeground(new Color(0, 100, 0));
		fldGuess.setBackground(new Color(255, 140, 0));
		fldGuess.setHorizontalAlignment(SwingConstants.CENTER);
		fldGuess.setBounds(10, 425, 20, 25);
		paneGame.add(fldGuess);
		fldGuess.setAutoscrolls(false);
		fldGuess.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		fldGuess.setColumns(10);
		fldGuess.addActionListener(this);
		fldGuess.addKeyListener(this);
		
		btnGuess = new JButton("\u21B5");
		btnGuess.setBounds(50, 425, 64, 23);
		btnGuess.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 165, 0), new Color(255, 165, 0), new Color(255, 140, 0), new Color(255, 140, 0)));
		btnGuess.setFocusPainted(false);
		btnGuess.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuess.setBackground(new Color(0, 100, 0));
		btnGuess.setForeground(new Color(255, 140, 0));
		paneGame.add(btnGuess);
		btnGuess.addActionListener(this);
		
		lblGuessedWord = new JLabel();
		lblGuessedWord.setForeground(new Color(255, 140, 0));
		lblGuessedWord.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGuessedWord.setBounds(76, 461, 314, 14);
		paneGame.add(lblGuessedWord);
		
		lblWord = new JLabel("Word:");
		lblWord.setForeground(new Color(255, 140, 0));
		lblWord.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWord.setBounds(10, 461, 46, 14);
		paneGame.add(lblWord);
		
		lblGuessed = new JLabel("Guessed:");
		lblGuessed.setForeground(new Color(255, 140, 0));
		lblGuessed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGuessed.setBounds(10, 486, 56, 14);
		paneGame.add(lblGuessed);
		
		lblGuessedLetters = new JLabel();
		lblGuessedLetters.setForeground(new Color(255, 140, 0));
		lblGuessedLetters.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGuessedLetters.setBounds(76, 486, 314, 14);
		paneGame.add(lblGuessedLetters);
		
		
		menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(0, 128, 0)));
		menuBar.setBackground(new Color(255, 140, 0));
		menuBar.setBounds(0, 0, 824, 21);
		paneGame.add(menuBar);
		
		mnOptions = new JMenu("Options");
		mnOptions.setForeground(new Color(0, 100, 0));
		mnOptions.setBackground(new Color(255, 140, 0));
		menuBar.add(mnOptions);
		
		mntmHint = new JMenuItem("Get Hint");
		mntmHint.setBorder(new LineBorder(new Color(0, 128, 0)));
		mntmHint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmHint.setForeground(new Color(0, 100, 0));
		mntmHint.setBackground(new Color(255, 140, 0));
		mnOptions.add(mntmHint);
		mntmHint.addActionListener(this);
		
		mntmRules = new JMenuItem("Rules");
		mntmRules.setBorder(new LineBorder(new Color(0, 128, 0)));
		mntmRules.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmRules.setForeground(new Color(0, 100, 0));
		mntmRules.setBackground(new Color(255, 140, 0));
		mnOptions.add(mntmRules);
		mntmRules.addActionListener(this);
		
		mntmScoreboard = new JMenuItem("Scoreboard");
		mntmScoreboard.setBorder(new LineBorder(new Color(0, 128, 0)));
		mntmScoreboard.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmScoreboard.setForeground(new Color(0, 100, 0));
		mntmScoreboard.setBackground(new Color(255, 140, 0));
		mnOptions.add(mntmScoreboard);
		mntmScoreboard.addActionListener(this);
		
		mntmNew = new JMenuItem("New Game");
		mntmNew.setBorder(new LineBorder(new Color(0, 128, 0)));
		mntmNew.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmNew.setForeground(new Color(0, 100, 0));
		mntmNew.setBackground(new Color(255, 140, 0));
		mnOptions.add(mntmNew);
		mntmNew.addActionListener(this);
		
		mntmQuit = new JMenuItem("Quit to Menu");
		mntmQuit.setBorder(new LineBorder(new Color(0, 128, 0)));
		mntmQuit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmQuit.setForeground(new Color(0, 100, 0));
		mntmQuit.setBackground(new Color(255, 140, 0));
		mnOptions.add(mntmQuit);
		mntmQuit.addActionListener(this);
		
		mntmClose = new JMenuItem("Close");
		mntmClose.setBorder(new LineBorder(new Color(0, 128, 0)));
		mntmClose.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmClose.setForeground(new Color(0, 100, 0));
		mntmClose.setBackground(new Color(255, 140, 0));
		mnOptions.add(mntmClose);
		mntmClose.addActionListener(this);
		
		
		btnNewPlr = new JButton("New Player");
		btnNewPlr.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 165, 0), new Color(255, 165, 0), new Color(255, 140, 0), new Color(255, 140, 0)));
		btnNewPlr.setFocusPainted(false);
		btnNewPlr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewPlr.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		btnNewPlr.setBackground(new Color(0, 100, 0));
		btnNewPlr.setForeground(new Color(255, 140, 0));
		paneWelcome.add(btnNewPlr);
		btnNewPlr.addActionListener(this);
		
		//Added in openWelcome
		btnReturn = new JButton("Returning Player");
		btnReturn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 165, 0), new Color(255, 165, 0), new Color(255, 140, 0), new Color(255, 140, 0)));
		btnReturn.setFocusPainted(false);
		btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturn.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		btnReturn.setBackground(new Color(0, 100, 0));
		btnReturn.setForeground(new Color(255, 140, 0));
		btnReturn.addActionListener(this);
		
		btnScoreboard = new JButton("View Scoreboard");
		btnScoreboard.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 165, 0), new Color(255, 165, 0), new Color(255, 140, 0), new Color(255, 140, 0)));
		btnScoreboard.setFocusPainted(false);
		btnScoreboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnScoreboard.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		btnScoreboard.setBackground(new Color(0, 100, 0));
		btnScoreboard.setForeground(new Color(255, 140, 0));
		btnScoreboard.addActionListener(this);

		btnLoad = new JButton("Load Saved Game");
		btnLoad.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 165, 0), new Color(255, 165, 0), new Color(255, 140, 0), new Color(255, 140, 0)));
		btnLoad.setFocusPainted(false);
		btnLoad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoad.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 11));
		btnLoad.setBackground(new Color(0, 100, 0));
		btnLoad.setForeground(new Color(255, 140, 0));
		btnLoad.addActionListener(this);
		
		this.openWelcome();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GameRunner frame = new GameRunner();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	//
	public void openGame()
	{
		JComponent[] readded = {btnReturn, btnScoreboard, btnLoad};
		paneGame.setVisible(true);
		//Remove components added during openWelcome
		for (JComponent c: readded)
		{
			if (c != null) //Make sure they are instantiated
				if (c.getParent() == paneWelcome)
					paneWelcome.remove(c);
		}
		paneWelcome.setVisible(false);
		mntmHint.setEnabled(true);
		this.setTitle("Hangman | Playing as " + game.getPlr().getName());
		this.setBounds(this.getX(), this.getY(), paneGame.getWidth(), paneGame.getHeight());
		updateGuessed();
	}
	public void openWelcome()
	{		
		Hangman savedGame = new Hangman();
		if (savedGame.load() > 0)
		{
			btnLoad.setText("Continue as " + savedGame.getPlr().getName());
			paneWelcome.add(btnLoad);
		}
		if (Hangman.getScoreboard().getSize() > 0) //Must occur after savedGame check in case there's a saved game but no scoreboard file
		{
			paneWelcome.add(btnReturn);
			paneWelcome.add(btnScoreboard);
		}
		for (int i = 0; i < paneWelcome.getComponents().length; i++)
		{
			paneWelcome.getComponents()[i].setBounds(40, (i * 34) + 11, 320, 23);
		}
		paneWelcome.setVisible(true);
		paneGame.setVisible(false);
		this.setTitle("Hangman | Main Menu");
		this.setBounds(this.getX(), this.getY(), paneWelcome.getWidth(), paneWelcome.getHeight());
	}
	
	public boolean displayImage(int version)
	{
		boolean flag = false;
		try
		{
			BufferedImage img = ImageIO.read(new File("images/hangman" + version + ".png"));
			Image simg = img.getScaledInstance(lblHangman.getWidth(), lblHangman.getHeight(), Image.SCALE_SMOOTH);
			lblHangman.setIcon(new ImageIcon(simg));
			flag = true;
		}
		catch (IOException e)
		{
			System.out.println("Could not read image file: " + "hangman" + version + ".png");
		}
		return flag;
	}
	
	public void loadGame()
	{
		game.load();
		openGame();
	}
	
	public void newPlayer()
	{
		String plrName = JOptionPane.showInputDialog(this, "Enter your username", "New Player", JOptionPane.QUESTION_MESSAGE);
		if (plrName != null) //If user does not exit or cancel prompt
		{
			try
			{
				game.newPlayer(plrName);
				openGame();
			}
			catch (ExistingEntryException e)
			{
				JOptionPane.showMessageDialog(this, "That name is already taken.\r\nPlease choose another or select \"Returning Player\"",
					"Name is Already Taken", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	public void newGame(String plrName)
	{
		game.newGame(plrName);
		openGame();
	}
	
	private void updateGuessed()
	{
		displayImage(game.getLimbs());
		lblGuessedWord.setText(game.displayGuessedInWord());
		lblGuessedLetters.setText(ListEnhancer.stringList(game.getGuessed(), " "));
	}
	
	public void guess()
	{
		if (!fldGuess.getText().isEmpty())
		{
			int guessResult = game.guess(fldGuess.getText().charAt(0));
			updateGuessed();
			switch (Math.abs(guessResult))
			{
				case 1: //Continue game
					break;
				case 2: //End game
					String endMsg;
					if (guessResult == 2) //Win
						endMsg = "You guessed the word and won! Play again?";
					else
						endMsg = "You didn't guess the word \"" + ListEnhancer.stringList(game.getWord(), "") + "\" and lost. Play again?";
					if (JOptionPane.showConfirmDialog(this, endMsg, "Game Over", JOptionPane.YES_NO_OPTION) == 1)
						openWelcome();
					else
						newGame(this.game.getPlr().getName());
					break;
			}
			fldGuess.setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLoad)
		{
			loadGame();
		}
		else if (e.getSource() == btnNewPlr)
		{
			newPlayer();
		}
		else if (e.getSource() == btnReturn)
		{
			String[] names = new String[Hangman.getScoreboard().getSize()];
			for (int i = 0; i < names.length; i++)
				names[i] = Hangman.getScoreboard().getPlayers().getElementAt(i).getName();
			String choice = (String) JOptionPane.showInputDialog(this, "Select the player you are returning as...", "Returning Player", JOptionPane.QUESTION_MESSAGE, null,
					names, names[0]);
				if (choice != null) //Will be null if prompt is cancelled or exited
				{
					newGame(choice);
				}
		}
		else if (e.getSource() == fldGuess || e.getSource() == btnGuess)
		{
			guess();
		}
		else if (e.getSource() == mntmHint)
		{
			if (game.hint())
				updateGuessed();
			else
			{
				JOptionPane.showMessageDialog(this, "You may not have any more hints if you have only 1 letter left.", "No More Hints", JOptionPane.WARNING_MESSAGE);
				mntmHint.setEnabled(false);
			}
		}
		else if (e.getSource() == mntmRules)
		{
			JOptionPane.showMessageDialog(this, "To goal of the game is to guess all of the letters of a randomly selected word.\r\n"
					+ "However, be careful! You can only guess 6 letters incorrectly before you lose the man is completely hung and you lose the game.\r\n"
					+ "If you guess all of the letters in the word before 6 incorrect letters, you win.",
					"Rules", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == mntmScoreboard || e.getSource() == btnScoreboard)
		{
			JTextPane scrbrd = new JTextPane();
			scrbrd.setText(Hangman.getScoreboard().displayScoreboard());
			scrbrd.setFont(new Font("Monospaced", Font.BOLD, 12));
			scrbrd.setHighlighter(null);
			JOptionPane.showMessageDialog(this, scrbrd, "Scoreboard", JOptionPane.PLAIN_MESSAGE);
		}
		else if (e.getSource() == mntmNew)
		{
			newGame(this.game.getPlr().getName());
		}
		else if (e.getSource() == mntmQuit)
		{
			if (!game.isEnded())
				game.save();
			openWelcome();
		}
		else if (e.getSource() == mntmClose)
		{
			if (!game.isEnded())
				game.save();
			this.dispose();
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getSource() == fldGuess)
		{
			Character input = e.getKeyChar();
			e.consume();
			//Prevent non-letter input and already guessed input
			if (Character.isLetter(input) && !ListEnhancer.includes(game.getGuessed(), input, true))
			{
				input = Character.toUpperCase(input);
				//Handles making sure no more than 1 character is inputed
				if (fldGuess.getText().length() >= 0)
				{
					fldGuess.setText(input.toString());
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if (!game.isEnded())
			game.save();
		this.dispose();
		System.exit(1);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
