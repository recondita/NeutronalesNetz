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
		setBounds(100, 100, 400, 589);
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
								.addComponent(logZuAufzeichnen, Alignment.LEADING)
								.addComponent(lblLogdateiAuswhlen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogZuAufzeichnen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(16))
						.addGroup(gl_aufzeichnen.createSequentialGroup()
							.addComponent(btnStartZuAufzeichnen, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(16))))
		);
		gl_aufzeichnen.setVerticalGroup(
			gl_aufzeichnen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_aufzeichnen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogdateiAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_aufzeichnen.createParallelGroup(Alignment.BASELINE)
						.addComponent(logZuAufzeichnen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogZuAufzeichnen))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuAufzeichnen, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
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
								.addComponent(btnLadenZuErstellen, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGroup(gl_erstellen.createSequentialGroup()
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_erstellen.createSequentialGroup()
											.addComponent(genomZuErstellen, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
											.addGap(2))
										.addComponent(lblGenomAuswhlen, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnGenomZuErstellen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_erstellen.createSequentialGroup()
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLayer)
										.addComponent(lblTransferfunktion))
									.addGap(43)
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(layerZuErstellen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(transferFunktion, 0, 214, Short.MAX_VALUE))
									.addGap(2)))
							.addGap(16))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_erstellen.createSequentialGroup()
									.addComponent(chckbxTrainierenZuErstellen)
									.addGap(54)
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxAutosaveZuErstellen)
										.addGroup(gl_erstellen.createParallelGroup(Alignment.TRAILING)
											.addComponent(learningRateZuErstellen, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
											.addComponent(momentumZuErstellen, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
											.addGroup(gl_erstellen.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
													.addComponent(maxIterationsZuErstellen, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
													.addComponent(maxTrainingZuErstellen, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))))))
								.addGroup(gl_erstellen.createSequentialGroup()
									.addComponent(lblMomentum)
									.addPreferredGap(ComponentPlacement.RELATED, 285, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMaximaleTrainingszeit)
								.addComponent(lblLearningrate))
							.addGap(18))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(lblMaximaleIterationen)
							.addContainerGap(254, Short.MAX_VALUE))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(lblAlsGenomSpeichern)
							.addContainerGap(256, Short.MAX_VALUE))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_erstellen.createSequentialGroup()
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addGap(68)
									.addComponent(speicherortZuAlsGenomSpeichern, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
									.addGap(6)
									.addComponent(btnSpeicherortZuAlsGenomSpeichern, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnErstellenZuErstellen, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
								.addGroup(gl_erstellen.createSequentialGroup()
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSpielaufzeichnung)
										.addComponent(lblSpeicherort))
									.addGap(37)
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
										.addComponent(spielaufzeichnungZuErstellen, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
										.addComponent(speicherortZuErstellen, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnSpeicherortZuErstellen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSpielaufzeichnungZuErstellen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
							.addGap(18))
						.addGroup(gl_erstellen.createSequentialGroup()
							.addComponent(btnAlsGenomSpeichern, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
							.addGap(18))))
		);
		gl_erstellen.setVerticalGroup(
			gl_erstellen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_erstellen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGenomAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(genomZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGenomZuErstellen))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLadenZuErstellen)
					.addPreferredGap(ComponentPlacement.RELATED)
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
						.addComponent(spielaufzeichnungZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSpielaufzeichnungZuErstellen)
						.addComponent(lblSpielaufzeichnung))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSpeicherort)
						.addComponent(speicherortZuErstellen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSpeicherortZuErstellen))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnErstellenZuErstellen, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAlsGenomSpeichern)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_erstellen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_erstellen.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_erstellen.createParallelGroup(Alignment.BASELINE)
								.addComponent(speicherortZuAlsGenomSpeichern, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5)))
						.addComponent(btnSpeicherortZuAlsGenomSpeichern))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAlsGenomSpeichern)
					.addContainerGap(23, Short.MAX_VALUE))
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
					.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_trainieren.createSequentialGroup()
							.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNetzwerkAuswhlen_1)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(gl_trainieren.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_trainieren.createSequentialGroup()
									.addComponent(netzwerkZuTrainieren, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNetzwerkZuTrainieren, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_trainieren.createSequentialGroup()
									.addComponent(spielaufzeichnungZuTrainieren, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSpielaufzeichnungZuTrainieren, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
							.addGap(16))
						.addGroup(gl_trainieren.createSequentialGroup()
							.addComponent(chckbxAutosaveZuTrainieren)
							.addContainerGap(266, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_trainieren.createSequentialGroup()
							.addGroup(gl_trainieren.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnStartZuTrainieren, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_trainieren.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addGap(62)
									.addComponent(learningRateZuTrainieren, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_trainieren.createSequentialGroup()
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addGap(71)
									.addComponent(momentumZuTrainieren, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_trainieren.createSequentialGroup()
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
									.addGap(16)
									.addComponent(maxTrainingZuTrainieren, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_trainieren.createSequentialGroup()
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addGap(22)
									.addComponent(maxIterationsZuTrainieren, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
							.addGap(18))))
		);
		gl_trainieren.setVerticalGroup(
			gl_trainieren.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_trainieren.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_trainieren.createParallelGroup(Alignment.BASELINE)
						.addComponent(netzwerkZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNetzwerkZuTrainieren)
						.addComponent(lblNetzwerkAuswhlen_1))
					.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_trainieren.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_trainieren.createSequentialGroup()
									.addGap(4)
									.addComponent(label))
								.addComponent(btnSpielaufzeichnungZuTrainieren)))
						.addGroup(gl_trainieren.createSequentialGroup()
							.addGap(7)
							.addComponent(spielaufzeichnungZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_trainieren.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(learningRateZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_trainieren.createSequentialGroup()
							.addGap(3)
							.addComponent(label_2))
						.addComponent(momentumZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_trainieren.createSequentialGroup()
							.addGap(3)
							.addComponent(label_3))
						.addComponent(maxTrainingZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_trainieren.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_trainieren.createSequentialGroup()
							.addGap(3)
							.addComponent(label_4))
						.addComponent(maxIterationsZuTrainieren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxAutosaveZuTrainieren)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuTrainieren, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(220, Short.MAX_VALUE))
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
				.addGap(0, 369, Short.MAX_VALUE)
				.addGroup(gl_spielen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_spielen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_spielen.createSequentialGroup()
							.addGroup(gl_spielen.createParallelGroup(Alignment.TRAILING)
								.addComponent(netzwerkZuSpielen, Alignment.LEADING, 310, 310, 310)
								.addComponent(lblNetzwerkAuswhlen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNetzwerkZuSpielen, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnStartZuSpielen, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addGap(16))
		);
		gl_spielen.setVerticalGroup(
			gl_spielen.createParallelGroup(Alignment.LEADING)
				.addGap(0, 474, Short.MAX_VALUE)
				.addGroup(gl_spielen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNetzwerkAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_spielen.createParallelGroup(Alignment.BASELINE)
						.addComponent(netzwerkZuSpielen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNetzwerkZuSpielen))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuSpielen, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(364, Short.MAX_VALUE))
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
		GroupLayout gl_evolution = new GroupLayout(evolution);
		gl_evolution.setHorizontalGroup(
			gl_evolution.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_evolution.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_evolution.createParallelGroup(Alignment.TRAILING)
						.addComponent(ausgabeKonsole, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_evolution.createSequentialGroup()
							.addGroup(gl_evolution.createParallelGroup(Alignment.TRAILING)
								.addComponent(speicherortZuEvolution, Alignment.LEADING, 310, 310, 310)
								.addComponent(lblSpeicherortAuswhlen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSpeicherortZuEvolution, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnStartZuEvolution, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addGap(16))
		);
		gl_evolution.setVerticalGroup(
			gl_evolution.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_evolution.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSpeicherortAuswhlen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_evolution.createParallelGroup(Alignment.BASELINE)
						.addComponent(speicherortZuEvolution, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSpeicherortZuEvolution))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnStartZuEvolution, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ausgabeKonsole, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
					.addContainerGap())
		);
		evolution.setLayout(gl_evolution);
	}
}
