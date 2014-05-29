package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.neuroph.util.TransferFunctionType;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField logZuAufzeichnen;
	private JTextField genomZuErstellen;
	private JTextField layerZuErstellen;
	private JTextField spielaufzeichnungZuErstellen;
	private JTextField momentumZuErstellen;
	private JTextField learningRateZuErstellen;
	private JTextField maxTrainingZuErstellen;
	private JTextField maxIterationsZuErstellen;
	private JTextField speicherortZuErstellen;
	private JTextField netzwerkZuSpielen;
	private JTextField netzwerkZuTrainieren;
	private JTextField spielaufzeichnungZuTrainieren;
	private JTextField maxIterationsZuTrainieren;
	private JTextField maxTrainingZuTrainieren;
	private JTextField momentumZuTrainieren;
	private JTextField learningRateZuTrainieren;
	private JTextField speicherortZuAlsGenomSpeichern;
	private JTextField speicherortZuEvolution;
	private JTextField maxTrainingZuEvolution;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setMinimumSize(new Dimension(350, 575));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel aufzeichnen = new JPanel();
		tabbedPane.addTab("Spiel aufzeichnen", null, aufzeichnen, null);
		
		logZuAufzeichnen = new JTextField();
		logZuAufzeichnen.setColumns(10);
		
		JButton btnLogZuAufzeichnen = new JButton("...");
		btnLogZuAufzeichnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblLogdateiAuswhlen = new JLabel("Logdatei ausw\u00E4hlen:");
		
		JButton btnStartZuAufzeichnen = new JButton("Start");
		GroupLayout gl_aufzeichnen = new GroupLayout(aufzeichnen);
		gl_aufzeichnen.setHorizontalGroup(
			gl_aufzeichnen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_aufzeichnen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_aufzeichnen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_aufzeichnen.createSequentialGroup()
							.addGroup(gl_aufzeichnen.createParallelGroup(Alignment.TRAILING)
								.addComponent(logZuAufzeichnen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addComponent(lblLogdateiAuswhlen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
							.addGap(6)
							.addComponent(btnLogZuAufzeichnen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnStartZuAufzeichnen, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_aufzeichnen.setVerticalGroup(
			gl_aufzeichnen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_aufzeichnen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogdateiAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_aufzeichnen.createParallelGroup(Alignment.BASELINE)
						.addComponent(logZuAufzeichnen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogZuAufzeichnen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuAufzeichnen, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(404, Short.MAX_VALUE))
		);
		aufzeichnen.setLayout(gl_aufzeichnen);
		
		JPanel erstellen = new JPanel();
		tabbedPane.addTab("Netz erstellen", null, erstellen, null);
		
		genomZuErstellen = new JTextField();
		genomZuErstellen.setColumns(10);
		
		JLabel lblGenomAuswhlen = new JLabel("Genom ausw\u00E4hlen:");
		
		JButton btnGenomZuErstellen = new JButton("...");
		btnGenomZuErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnLadenZuErstellen = new JButton("Laden");
		
		JComboBox transferFunktion = new JComboBox();
		transferFunktion.setModel(new DefaultComboBoxModel(TransferFunctionType.values()));
		
		JLabel lblTransferfunktion = new JLabel("Transferfunktion:");
		
		JLabel lblLayer = new JLabel("Layer:");
		
		layerZuErstellen = new JTextField();
		layerZuErstellen.setToolTipText("Einzelne Schichten bitte durch einen Punkt trennen");
		layerZuErstellen.setColumns(10);
		
		JLabel lblLearningrate = new JLabel("Learningrate:");
		
		JLabel lblMomentum = new JLabel("Momentum:");
		
		JLabel lblMaximaleTrainingszeit = new JLabel("Maximale Trainingszeit:");
		
		spielaufzeichnungZuErstellen = new JTextField();
		spielaufzeichnungZuErstellen.setColumns(10);
		
		JButton btnSpielaufzeichnungZuErstellen = new JButton("...");
		
		JLabel lblSpielaufzeichnung = new JLabel("Spielaufzeichnung:");
		
		momentumZuErstellen = new JTextField();
		momentumZuErstellen.setColumns(10);
		
		learningRateZuErstellen = new JTextField();
		learningRateZuErstellen.setColumns(10);
		
		maxTrainingZuErstellen = new JTextField();
		maxTrainingZuErstellen.setColumns(10);
		
		JCheckBox chckbxTrainierenZuErstellen = new JCheckBox("Trainieren");
		chckbxTrainierenZuErstellen.setSelected(true);
		
		JLabel lblMaximaleIterationen = new JLabel("Maximale Iterationen:");
		
		maxIterationsZuErstellen = new JTextField();
		maxIterationsZuErstellen.setColumns(10);
		
		JLabel lblSpeicherort = new JLabel("Speicherort:");
		
		speicherortZuErstellen = new JTextField();
		speicherortZuErstellen.setColumns(10);
		
		JButton btnSpeicherortZuErstellen = new JButton("...");
		
		JButton btnErstellenZuErstellen = new JButton("Erstellen");
		
		JCheckBox chckbxAutosaveZuErstellen = new JCheckBox("Autosave");
		
		JLabel lblAlsGenomSpeichern = new JLabel("Als Genom speichern:");
		
		JLabel label_5 = new JLabel("Speicherort:");
		
		speicherortZuAlsGenomSpeichern = new JTextField();
		speicherortZuAlsGenomSpeichern.setColumns(10);
		
		JButton btnSpeicherortZuAlsGenomSpeichern = new JButton("...");
		
		JButton btnAlsGenomSpeichern = new JButton("Als Genom speichern");
		GroupLayout gl_erstellen = new GroupLayout(erstellen);
		gl_erstellen.setHorizontalGroup(
			gl_erstellen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_erstellen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_erstellen.createSequentialGroup()
							.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_erstellen.createSequentialGroup()
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(lblGenomAuswhlen, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
										.addComponent(genomZuErstellen, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
									.addGap(6)
									.addComponent(btnGenomZuErstellen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_erstellen.createSequentialGroup()
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLayer)
										.addComponent(lblTransferfunktion))
									.addGap(48)
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(transferFunktion, 0, 193, Short.MAX_VALUE)
										.addComponent(chckbxAutosaveZuErstellen)
										.addComponent(layerZuErstellen, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
									.addGap(0)))
							.addContainerGap())
						.addGroup(gl_erstellen.createSequentialGroup()
							.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_erstellen.createSequentialGroup()
									.addComponent(chckbxTrainierenZuErstellen)
									.addGap(54)
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_erstellen.createSequentialGroup()
											.addGap(9)
											.addComponent(learningRateZuErstellen, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
										.addGroup(gl_erstellen.createSequentialGroup()
											.addGap(9)
											.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
												.addComponent(maxTrainingZuErstellen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
												.addComponent(momentumZuErstellen, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
										.addGroup(Alignment.TRAILING, gl_erstellen.createSequentialGroup()
											.addGap(9)
											.addComponent(maxIterationsZuErstellen, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addGroup(gl_erstellen.createSequentialGroup()
									.addComponent(lblMomentum)
									.addPreferredGap(ComponentPlacement.RELATED, 272, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMaximaleTrainingszeit)
								.addComponent(lblLearningrate))
							.addGap(12))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(lblMaximaleIterationen)
							.addContainerGap(235, Short.MAX_VALUE))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(lblSpielaufzeichnung)
							.addGap(39)
							.addComponent(spielaufzeichnungZuErstellen, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btnSpielaufzeichnungZuErstellen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(lblSpeicherort)
							.addGap(76)
							.addComponent(speicherortZuErstellen, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSpeicherortZuErstellen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(btnErstellenZuErstellen, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(lblAlsGenomSpeichern)
							.addContainerGap(233, Short.MAX_VALUE))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(label_5)
							.addGap(76)
							.addComponent(speicherortZuAlsGenomSpeichern, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSpeicherortZuAlsGenomSpeichern, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(btnAlsGenomSpeichern, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(btnLadenZuErstellen, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_erstellen.setVerticalGroup(
			gl_erstellen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_erstellen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGenomAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(genomZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGenomZuErstellen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLadenZuErstellen)
					.addGap(12)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(transferFunktion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTransferfunktion))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLayer)
						.addComponent(layerZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxTrainierenZuErstellen)
						.addComponent(chckbxAutosaveZuErstellen))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLearningrate)
						.addComponent(learningRateZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMomentum)
						.addComponent(momentumZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximaleTrainingszeit)
						.addComponent(maxTrainingZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximaleIterationen)
						.addComponent(maxIterationsZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSpielaufzeichnungZuErstellen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSpielaufzeichnung)
						.addComponent(spielaufzeichnungZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(speicherortZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSpeicherortZuErstellen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSpeicherort))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnErstellenZuErstellen, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAlsGenomSpeichern)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_erstellen.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
								.addComponent(speicherortZuAlsGenomSpeichern, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5)))
						.addComponent(btnSpeicherortZuAlsGenomSpeichern, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAlsGenomSpeichern)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		erstellen.setLayout(gl_erstellen);
		
		JPanel trainieren = new JPanel();
		tabbedPane.addTab("Netz trainieren", null, trainieren, null);
		
		netzwerkZuTrainieren = new JTextField();
		netzwerkZuTrainieren.setColumns(10);
		
		JLabel lblNetzwerkAuswhlen_1 = new JLabel("Netzwerk ausw\u00E4hlen:");
		
		JButton btnNetzwerkZuTrainieren = new JButton("...");
		
		JLabel label = new JLabel("Spielaufzeichnung:");
		
		spielaufzeichnungZuTrainieren = new JTextField();
		spielaufzeichnungZuTrainieren.setColumns(10);
		
		JButton btnSpielaufzeichnungZuTrainieren = new JButton("...");
		
		JLabel label_1 = new JLabel("Learningrate:");
		
		JLabel label_2 = new JLabel("Momentum:");
		
		JLabel label_3 = new JLabel("Maximale Trainingszeit:");
		
		JLabel label_4 = new JLabel("Maximale Iterationen:");
		
		maxIterationsZuTrainieren = new JTextField();
		maxIterationsZuTrainieren.setColumns(10);
		
		maxTrainingZuTrainieren = new JTextField();
		maxTrainingZuTrainieren.setColumns(10);
		
		momentumZuTrainieren = new JTextField();
		momentumZuTrainieren.setColumns(10);
		
		learningRateZuTrainieren = new JTextField();
		learningRateZuTrainieren.setColumns(10);
		
		JCheckBox chckbxAutosaveZuTrainieren = new JCheckBox("Autosave");
		
		JButton btnStartZuTrainieren = new JButton("Start");
		GroupLayout gl_trainieren = new GroupLayout(trainieren);
		gl_trainieren.setHorizontalGroup(
			gl_trainieren.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_trainieren.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_trainieren.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_trainieren.createSequentialGroup()
							.addComponent(label_4)
							.addGap(24)
							.addComponent(maxIterationsZuTrainieren, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_trainieren.createSequentialGroup()
							.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_trainieren.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_trainieren.createSequentialGroup()
										.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNetzwerkAuswhlen_1)
											.addComponent(label))
										.addGap(24))
									.addGroup(gl_trainieren.createSequentialGroup()
										.addComponent(label_1)
										.addGap(70)))
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_trainieren.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_trainieren.createSequentialGroup()
									.addComponent(learningRateZuTrainieren, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
									.addContainerGap())
								.addGroup(gl_trainieren.createSequentialGroup()
									.addGroup(gl_trainieren.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_trainieren.createSequentialGroup()
											.addComponent(netzwerkZuTrainieren, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnNetzwerkZuTrainieren, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_trainieren.createSequentialGroup()
											.addComponent(spielaufzeichnungZuTrainieren, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnSpielaufzeichnungZuTrainieren, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(12))
								.addGroup(gl_trainieren.createSequentialGroup()
									.addComponent(momentumZuTrainieren, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
									.addContainerGap())))
						.addGroup(gl_trainieren.createSequentialGroup()
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(maxTrainingZuTrainieren, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_trainieren.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
							.addComponent(chckbxAutosaveZuTrainieren)
							.addContainerGap())
						.addGroup(gl_trainieren.createSequentialGroup()
							.addComponent(btnStartZuTrainieren, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_trainieren.setVerticalGroup(
			gl_trainieren.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_trainieren.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_trainieren.createParallelGroup(Alignment.BASELINE)
						.addComponent(netzwerkZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNetzwerkZuTrainieren, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNetzwerkAuswhlen_1))
					.addGap(7)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.BASELINE)
						.addComponent(spielaufzeichnungZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(btnSpielaufzeichnungZuTrainieren, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(learningRateZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.BASELINE)
						.addComponent(momentumZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(maxTrainingZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.BASELINE)
						.addComponent(maxIterationsZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(8)
					.addComponent(chckbxAutosaveZuTrainieren)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuTrainieren, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(263, Short.MAX_VALUE))
		);
		trainieren.setLayout(gl_trainieren);
		
		JPanel spielen = new JPanel();
		tabbedPane.addTab("Spielen lassen", null, spielen, null);
		
		netzwerkZuSpielen = new JTextField();
		netzwerkZuSpielen.setColumns(10);
		
		JLabel lblNetzwerkAuswhlen = new JLabel("Netzwerk ausw\u00E4hlen:");
		
		JButton btnNetzwerkZuSpielen = new JButton("...");
		
		JButton btnStartZuSpielen = new JButton("Start");
		GroupLayout gl_spielen = new GroupLayout(spielen);
		gl_spielen.setHorizontalGroup(
			gl_spielen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_spielen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_spielen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_spielen.createSequentialGroup()
							.addGroup(gl_spielen.createParallelGroup(Alignment.TRAILING)
								.addComponent(netzwerkZuSpielen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
								.addComponent(lblNetzwerkAuswhlen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
							.addGap(6)
							.addComponent(btnNetzwerkZuSpielen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnStartZuSpielen, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_spielen.setVerticalGroup(
			gl_spielen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_spielen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNetzwerkAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_spielen.createParallelGroup(Alignment.BASELINE)
						.addComponent(netzwerkZuSpielen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNetzwerkZuSpielen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuSpielen, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(404, Short.MAX_VALUE))
		);
		spielen.setLayout(gl_spielen);
		
		JPanel evolution = new JPanel();
		tabbedPane.addTab("Evolution", null, evolution, null);
		
		speicherortZuEvolution = new JTextField();
		speicherortZuEvolution.setColumns(10);
		
		JLabel lblSpeicherortAuswhlen = new JLabel("Speicherort ausw\u00E4hlen:");
		
		JButton btnSpeicherortZuEvolution = new JButton("...");
		
		JButton btnStartZuEvolution = new JButton("Start");
		
		JTextArea ausgabeKonsole = new JTextArea();
		
		JLabel label_6 = new JLabel("Maximale Trainingszeit:");
		
		maxTrainingZuEvolution = new JTextField();
		maxTrainingZuEvolution.setColumns(10);
		GroupLayout gl_evolution = new GroupLayout(evolution);
		gl_evolution.setHorizontalGroup(
			gl_evolution.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_evolution.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_evolution.createParallelGroup(Alignment.TRAILING)
						.addComponent(ausgabeKonsole, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_evolution.createSequentialGroup()
							.addGroup(gl_evolution.createParallelGroup(Alignment.TRAILING)
								.addComponent(speicherortZuEvolution, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
								.addComponent(lblSpeicherortAuswhlen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
							.addGap(6)
							.addComponent(btnSpeicherortZuEvolution, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_evolution.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(maxTrainingZuEvolution, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
						.addComponent(btnStartZuEvolution, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_evolution.setVerticalGroup(
			gl_evolution.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_evolution.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSpeicherortAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_evolution.createParallelGroup(Alignment.BASELINE)
						.addComponent(speicherortZuEvolution, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSpeicherortZuEvolution, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_evolution.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_evolution.createSequentialGroup()
							.addGap(2)
							.addComponent(label_6))
						.addComponent(maxTrainingZuEvolution, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuEvolution, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ausgabeKonsole, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addContainerGap())
		);
		evolution.setLayout(gl_evolution);
	}
}