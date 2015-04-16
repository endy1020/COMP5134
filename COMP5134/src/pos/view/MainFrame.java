package pos.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pos.controller.PosManager;
import pos.model.Decorator;
import pos.model.IceCream;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private JFrame sysAdminFrame;
	private JFrame addItemFrame;
	private String subject[] = {"[ICE-CREAM Flavor]","      ", "[Decorator]","      "};
	private JButton iceCreamButton;
	private JButton decoratorButton;
	private JPanel sysAdminPanel = new JPanel();
	private JLabel errorMessage = new JLabel();
	int checkList = 1;
	
	private List<JButton> iceCreamButtonList = new ArrayList<JButton>();
	private List<JButton> decoratorButtonList = new ArrayList<JButton>();
	private JButton startButton = new JButton("New IceCream");
	private JButton sysAdminButton = new JButton("System Administrator");
	private JLabel printOutLabel = new JLabel();
	private JLabel totalLabel = new JLabel("   Total: ");
	private JButton addIceCreamButton;
	private JButton addDecoratorButton;
	
	private List<IceCream> iceCreamList = new ArrayList<IceCream>();
	private List<IceCream> decoratorList = new ArrayList<IceCream>();
	private List<IceCream> selectionList = new ArrayList<IceCream>();

	public MainFrame(){

		//Frame size
		mainFrame = new JFrame("POS-----Ice-Cream Shop");
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new GridBagLayout());
		for(int i = 0; i < 4; i++)
		{
			//Subject List
			JLabel n0 = new JLabel(subject[i]);
			GridBagConstraints c0 = new GridBagConstraints();
			c0.gridx = i;
			c0.gridy = 0;
			c0.gridwidth = 1;
			c0.gridheight = 1;
			c0.weightx = 0.0;
			c0.weighty = 0.0;
			c0.fill = 0;
			c0.anchor = 10;
			mainFrame.add(n0, c0);
		}


		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 4;
		c1.gridy = 0;
		c1.gridwidth = 1;
		c1.gridheight = 1;
		c1.weightx = 0.0;
		c1.weighty = 0.0;
		c1.fill = 0;
		c1.anchor = 10;
		mainFrame.add(startButton, c1);
		startButton.addActionListener(new AfterButtonClickListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setIceCreamButtonEnabled(true);
				setDecoratorButtonEnabled(false);
				selectionList = new ArrayList<IceCream>();
				totalLabel.setText("   Total: ");
				if( sysAdminFrame != null){
				sysAdminFrame.dispose();
				}
				refreshResult();
			}
		});

		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 5;
		c2.gridy = 0;
		c2.gridwidth = 1;
		c2.gridheight = 1;
		c2.weightx = 0.0;
		c2.weighty = 0.0;
		c2.fill = 0;
		c2.anchor = 10;
		mainFrame.add(sysAdminButton, c2);
		//sysAdminButton.setActionCommand("sysAdmin");
		sysAdminButton.addActionListener(new AfterButtonClickListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addItem();
				setIceCreamButtonEnabled(false);
				setDecoratorButtonEnabled(false);
			}
		});


		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 7;
		c3.gridy = 0;
		c3.gridwidth = 1;
		c3.gridheight = 1;
		c3.weightx = 0.0;
		c3.weighty = 0.0;
		c3.fill = 0;
		c3.anchor = 10;
		mainFrame.add(totalLabel, c3);
		
		mainFrame.setVisible(true);
		
		
	}
	
	private void addItem(){
		if( sysAdminFrame == null || !sysAdminFrame.isShowing() ){
			sysAdminFrame = new JFrame("New item");
			sysAdminFrame.setSize(300,200);
			sysAdminFrame.setVisible(true);
			Container cp = sysAdminFrame.getContentPane();
			cp.setLayout(new GridLayout(2,3)); 

			addIceCreamButton = new JButton("New Ice Cream");
			addDecoratorButton = new JButton("New Decorator");

			addIceCreamButton.addActionListener(new AfterButtonClickListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					addItemFrame = new JFrame("New Ice Cream");
					addItemFrame.setSize(500,200);
					//sysAdminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					addItemFrame.setVisible(true);
					JLabel  descriptionLabel = new JLabel("Ice Cream: ");
					final JTextField description  = new JTextField(10);
					JLabel  priceLabel = new JLabel("Price: ");
					final JTextField price  = new JTextField(10);
					JButton addToSystem = new JButton("Add");
					sysAdminPanel = new JPanel();
					sysAdminPanel.add(descriptionLabel);
					sysAdminPanel.add(description);
					sysAdminPanel.add(priceLabel);
					sysAdminPanel.add(price);
					sysAdminPanel.add(addToSystem);
					addItemFrame.add(sysAdminPanel);

					addToSystem.addActionListener(new AfterButtonClickListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							int returnCode = PosManager.validIceCream(description.getText(), price.getText());
							if( returnCode == 0 ){
								IceCream c = new IceCream();
								c.setDescription(description.getText());
								c.setPrice(Double.parseDouble(price.getText()));
								addIceCream(c);	
							}else{
								errorMessage(returnCode);
							}
						}
					});
				}
			});

			addDecoratorButton.addActionListener(new AfterButtonClickListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					addItemFrame = new JFrame("New Decorator");
					addItemFrame.setSize(500,200);
					//sysAdminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					addItemFrame.setVisible(true);
					JLabel  descriptionLabel = new JLabel("Decorator: ");
					final JTextField description  = new JTextField(10);
					JLabel  priceLabel = new JLabel("Price: ");
					final JTextField price  = new JTextField(10);
					JButton addToSystem = new JButton("Add");
					sysAdminPanel = new JPanel();
					sysAdminPanel.add(descriptionLabel);
					sysAdminPanel.add(description);
					sysAdminPanel.add(priceLabel);
					sysAdminPanel.add(price);
					sysAdminPanel.add(addToSystem);
					addItemFrame.add(sysAdminPanel);

					addToSystem.addActionListener(new AfterButtonClickListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							int returnCode = PosManager.validIceCream(description.getText(), price.getText());
							if( returnCode == 0 ){
								Decorator d = new Decorator();
								d.setDescription(description.getText());
								d.setPrice(Double.parseDouble(price.getText()));
								addDecorator(d);	
							}else{
								errorMessage(returnCode);
							}
						}
					});
				}
			});

			addIceCreamButton.setActionCommand("newIceCream");
			addDecoratorButton.setActionCommand("newDecorator");

			cp.add(addIceCreamButton);
			cp.add(addDecoratorButton);
		}
	}

	private void addIceCream(IceCream iceCream){
		iceCreamList.add(iceCream);
		int row = 1;
		for(final IceCream i: iceCreamList)
		{	
			iceCreamButton = new JButton(i.getDescription()+", $"+i.getPrice());
			iceCreamButtonList.add(iceCreamButton);
			iceCreamButton.setEnabled(false);
			iceCreamButton.addActionListener(new AfterButtonClickListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					selectionList.add(i);
					setDecoratorButtonEnabled(true);
					setIceCreamButtonEnabled(false);
					totalLabel.setText("   Total: "+PosManager.calTotalPrice(selectionList));
					refreshResult();
				}
			});
			GridBagConstraints cIceCream = new GridBagConstraints();
			cIceCream.gridx = 0;
			cIceCream.gridy = row;
			cIceCream.gridwidth = 1;
			cIceCream.gridheight = 1;
			cIceCream.weightx = 0.0;
			cIceCream.weighty = 0.0;
			cIceCream.fill = 2;
			cIceCream.anchor = 10;
			mainFrame.add(iceCreamButton, cIceCream);
			mainFrame.revalidate();
			mainFrame.repaint();
			row++;
		}
	}

	private void addDecorator(Decorator decorator){
		decoratorList.add(decorator);
		int row = 1;
		for(final IceCream i: decoratorList)
		{	
			decoratorButton = new JButton(i.getDescription()+", $"+i.getPrice());
			decoratorButtonList.add(decoratorButton);
			decoratorButton.setEnabled(false);
			decoratorButton.addActionListener(new AfterButtonClickListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					selectionList.add(i);
					totalLabel.setText("   Total: "+PosManager.calTotalPrice(selectionList));
					refreshResult();
				}
			});
			GridBagConstraints cdecorator = new GridBagConstraints();
			cdecorator.gridx = 2;
			cdecorator.gridy = row;
			cdecorator.gridwidth = 1;
			cdecorator.gridheight = 1;
			cdecorator.weightx = 0.0;
			cdecorator.weighty = 0.0;
			cdecorator.fill = 2;
			cdecorator.anchor = 10;
			mainFrame.add(decoratorButton, cdecorator);
			mainFrame.revalidate();
			mainFrame.repaint();
			row++;
		}
	}

	private void setDecoratorButtonEnabled(boolean enabled){

		for(JButton j: decoratorButtonList){
			j.setEnabled(enabled);
		}
	}

	private void setIceCreamButtonEnabled(boolean enabled){

		for(JButton i: iceCreamButtonList){
			i.setEnabled(enabled);
		}
	}

public void preSet(List<IceCream> icList, List<IceCream> decList){
		
		for(IceCream c : icList){
			addIceCream(c);
		}
		
		for(IceCream d : decList){
			addDecorator((Decorator)d);
		}
		
	}

	private void refreshResult(){
		StringBuilder resultStringBuilder = new StringBuilder(); 
		
		if (iceCreamButtonList.size() > decoratorButtonList.size()){
			checkList = iceCreamButtonList.size() + 1;
		}
		else
		{
			checkList = decoratorButtonList.size() + 1;
		}

		for(IceCream i : selectionList ){
			if(resultStringBuilder.length() == 0){
				resultStringBuilder.append(i.getDescription()+" Ice-cream");  
			}else{
				resultStringBuilder.append(" + "+i.getDescription());
			}
		}

		printOutLabel.setText(resultStringBuilder.toString());

		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 0;
		c4.gridy = checkList;
		c4.gridwidth = 0;
		c4.gridheight = 0;
		c4.weightx = 0.0;
		c4.weighty = 0.0;
		c4.fill = 0;
		c4.anchor = 10;
		mainFrame.add(printOutLabel, c4);

	}
	
	private void errorMessage(int returnCode){
		
		
		if (returnCode == 1){
			errorMessage.setText("Please insert description name!");
		}
		if (returnCode == 2){
			errorMessage.setText("Please insert valid price!");
		}
		
		sysAdminPanel.add(errorMessage);
		addItemFrame.add(sysAdminPanel);
		addItemFrame.revalidate();
		addItemFrame.repaint();
	}

	class AfterButtonClickListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//no use
		}
	}
	
}
