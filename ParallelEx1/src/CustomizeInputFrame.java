import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class CustomizeInputFrame extends JFrame implements ActionListener{

	private JRadioButton unsafePassingRadioButton, safePassingRadioButton, crossImmediatelyRadioButton, notFairRadioButton, alternatelyRadioButton, alternatelyWithAdjustmentsRadioButton;
	private JTextField carArrivalRate, crossingTime;
	public CustomizeInputFrame()
	{
		JPanel panelA = new JPanel();
		panelA.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));


		panelA.add(this.firstSection());		
		panelA.add(this.secondSection());
		panelA.add(this.thirdSection());

		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		panelA.add(ok);
		
		this.setContentPane(panelA);
		this.pack();
		this.setVisible(true);
		//this.setSize(600,650);
		this.setTitle("Customize Input");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel firstSection()
	{
		JPanel p = new JPanel();

		unsafePassingRadioButton = new JRadioButton("Unsafe Passing");
		safePassingRadioButton = new JRadioButton("Safe Passing");

		unsafePassingRadioButton.addActionListener(this);
		safePassingRadioButton.addActionListener(this);

		Border blackline = BorderFactory.createTitledBorder("Safety");
		p.setBorder(blackline);


		ButtonGroup group = new ButtonGroup();
		group.add(unsafePassingRadioButton);
		group.add(safePassingRadioButton);


		safePassingRadioButton.setSelected(true);
		p.setLayout(new GridLayout(2, 1, 0, 10));
		p.add(unsafePassingRadioButton);p.add(safePassingRadioButton);

		return p;
	}

	public JPanel secondSection()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		Border blackline = BorderFactory.createTitledBorder("Rate");
		p.setBorder(blackline);
		
		
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth = 1;
		carArrivalRate = new JTextField(7);
		gbc.insets = new Insets(15, 0, 5, 5);
		p.add(carArrivalRate,gbc);
		
		gbc.gridx++;
		gbc.insets = new Insets(15, 5, 5, 0);
		p.add(new JLabel("cars/sec"),gbc);
		
		
		gbc.gridx--;
		gbc.gridy++;
		crossingTime = new JTextField(7);
		gbc.insets = new Insets(5, 0, 15, 5);
		p.add(crossingTime,gbc);
		
		gbc.gridx++;
		gbc.insets = new Insets(5, 5, 15, 0);
		p.add(new JLabel("crossing time (secs)"),gbc);
		


		return p;
	}

	public JPanel thirdSection()
	{
		JPanel p = new JPanel(); 
		crossImmediatelyRadioButton = new JRadioButton("Cross immediately");
		notFairRadioButton = new JRadioButton("Not fair");
		alternatelyRadioButton = new JRadioButton("Alternately");
		alternatelyWithAdjustmentsRadioButton = new JRadioButton("Alternately with adjustments");

		crossImmediatelyRadioButton.addActionListener(this);
		notFairRadioButton.addActionListener(this);
		alternatelyRadioButton.addActionListener(this);
		alternatelyWithAdjustmentsRadioButton.addActionListener(this);



		Border blackline = BorderFactory.createTitledBorder("Algorithm");
		p.setBorder(blackline);


		ButtonGroup group = new ButtonGroup();
		group.add(crossImmediatelyRadioButton);
		group.add(notFairRadioButton);
		group.add(alternatelyRadioButton);
		group.add(alternatelyWithAdjustmentsRadioButton);

  
		notFairRadioButton.setSelected(true);
		p.setLayout(new GridLayout(4, 1, 0, 10));
		p.add(crossImmediatelyRadioButton);p.add(notFairRadioButton);p.add(alternatelyRadioButton);p.add(alternatelyWithAdjustmentsRadioButton);

		return p;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(unsafePassingRadioButton))
		{
			crossImmediatelyRadioButton.setSelected(true);
		}
		else if(e.getSource().equals(safePassingRadioButton))
		{
			if(crossImmediatelyRadioButton.isSelected())
				notFairRadioButton.setSelected(true);
		}
		else if(e.getSource().equals(notFairRadioButton)||e.getSource().equals(alternatelyRadioButton)||e.getSource().equals(alternatelyWithAdjustmentsRadioButton))
		{
			safePassingRadioButton.setSelected(true);
		}
		else if(e.getSource().equals(crossImmediatelyRadioButton))
		{
			unsafePassingRadioButton.setSelected(true);
		}
		else if(e.getActionCommand().equals("OK"))
		{
			boolean rateIsDouble, crossingTimeIsDouble;
			try
			{
				Double.parseDouble(carArrivalRate.getText());
				rateIsDouble = true;
			}
			catch (NumberFormatException k)
			{
				carArrivalRate.setText("need double");
				rateIsDouble = false;
			}
			try
			{
				Double.parseDouble(crossingTime.getText());
				crossingTimeIsDouble = true;
			}
			catch (NumberFormatException k)
			{
				crossingTime.setText("need double");
				crossingTimeIsDouble = false;
			}
			
			//If "OK" button is a legal action
			if(rateIsDouble&&crossingTimeIsDouble)
			{
				
			}
		}
	}
}
