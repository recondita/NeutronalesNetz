package gui;

import genetik.EVKontrolle;
import genetik.GenNetz;
import genetik.Genom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

import netz.SnakeTrainer;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

import snakeCOM.SnakeLogger;
import snakeCOM.SnakePlayer;
import util.MyUtils;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
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
	private JTextField speicherortZuAlsGenomSpeichern;
	private JTextField speicherortZuEvolution;
	private JTextField maxTrainingZuEvolution;
	private JTextField individuenZuEvolution;
	private JTextField testsZuEvolution;
	private JTextArea ausgabeKonsole;
	PrintStream consoleStreamforTextArea = new PrintStream(System.out) {

		@Override
		public void println(String s) {
			updateFeld(s);
		}
	};
	private JTextField generationenZuEvolution;
	private JTextField spielaufzeichnungZuEvolution;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("KNN");
		setMinimumSize(new Dimension(350, 575));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 179, 575);
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
				String pfad = chooseFileToSave("Snakelog", "slog");
				if (pfad != null)
					logZuAufzeichnen.setText(pfad);

			}
		});

		JLabel lblLogdateiAuswhlen = new JLabel("Logdatei ausw\u00E4hlen:");

		JButton btnStartZuAufzeichnen = new JButton("Start");
		btnStartZuAufzeichnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SnakeLogger(new File(logZuAufzeichnen.getText()));
			}

		});
		GroupLayout gl_aufzeichnen = new GroupLayout(aufzeichnen);
		gl_aufzeichnen
				.setHorizontalGroup(gl_aufzeichnen
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_aufzeichnen
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_aufzeichnen
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_aufzeichnen
																		.createSequentialGroup()
																		.addGroup(
																				gl_aufzeichnen
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								logZuAufzeichnen,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								302,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblLogdateiAuswhlen,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								302,
																								Short.MAX_VALUE))
																		.addGap(6)
																		.addComponent(
																				btnLogZuAufzeichnen,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																btnStartZuAufzeichnen,
																GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_aufzeichnen
				.setVerticalGroup(gl_aufzeichnen
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_aufzeichnen
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblLogdateiAuswhlen)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_aufzeichnen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																logZuAufzeichnen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnLogZuAufzeichnen,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(btnStartZuAufzeichnen,
												GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(404, Short.MAX_VALUE)));
		aufzeichnen.setLayout(gl_aufzeichnen);

		JPanel erstellen = new JPanel();
		tabbedPane.addTab("Netz erstellen", null, erstellen, null);

		genomZuErstellen = new JTextField();
		genomZuErstellen.setColumns(10);

		JLabel lblGenomAuswhlen = new JLabel("Genom ausw\u00E4hlen:");

		JButton btnGenomZuErstellen = new JButton("...");
		btnGenomZuErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfad = chooseFileToOpen("Genom", "gen");
				if (pfad != null)
					genomZuErstellen.setText(pfad);
			}
		});

		JButton btnLadenZuErstellen = new JButton("Laden");

		final JComboBox<TransferFunctionType> transferFunktion = new JComboBox<TransferFunctionType>();
		transferFunktion
				.setModel(new DefaultComboBoxModel<TransferFunctionType>(
						TransferFunctionType.values()));

		JLabel lblTransferfunktion = new JLabel("Transferfunktion:");

		JLabel lblLayer = new JLabel("Layer:");

		layerZuErstellen = new JTextField();
		layerZuErstellen
				.setToolTipText("Einzelne Schichten bitte durch einen Leerzeichen trennen");
		layerZuErstellen.setColumns(10);

		JLabel lblLearningrate = new JLabel("Learningrate:");

		JLabel lblMomentum = new JLabel("Momentum:");

		JLabel lblMaximaleTrainingszeit = new JLabel("Maximale Trainingszeit:");

		spielaufzeichnungZuErstellen = new JTextField();
		spielaufzeichnungZuErstellen.setColumns(10);

		final JButton btnSpielaufzeichnungZuErstellen = new JButton("...");
		btnSpielaufzeichnungZuErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfad = chooseFileToOpen("Snakelog", "slog");
				if (pfad != null)
					spielaufzeichnungZuErstellen.setText(pfad);
			}
		});

		JLabel lblSpielaufzeichnung = new JLabel("Spielaufzeichnung:");

		momentumZuErstellen = new JTextField();
		momentumZuErstellen.setToolTipText("Liegt im Wertebereich 0<=x<=1");
		momentumZuErstellen.setColumns(10);

		learningRateZuErstellen = new JTextField();
		learningRateZuErstellen.setToolTipText("Liegt im Wertebereich 0<=x<=1");
		learningRateZuErstellen.setColumns(10);

		maxTrainingZuErstellen = new JTextField();
		maxTrainingZuErstellen.setToolTipText("In Sekunden");
		maxTrainingZuErstellen.setColumns(10);

		final JCheckBox chckbxTrainierenZuErstellen = new JCheckBox(
				"Trainieren");
		chckbxTrainierenZuErstellen.setSelected(true);

		JLabel lblMaximaleIterationen = new JLabel("Maximale Iterationen:");

		maxIterationsZuErstellen = new JTextField();
		maxIterationsZuErstellen
				.setToolTipText("Hier bitte eine Ganzzahl verwenden");
		maxIterationsZuErstellen.setColumns(10);

		JLabel lblSpeicherort = new JLabel("Speicherort:");

		speicherortZuErstellen = new JTextField();
		speicherortZuErstellen.setColumns(10);

		JButton btnSpeicherortZuErstellen = new JButton("...");
		btnSpeicherortZuErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfad = chooseFileToSave("Neuronal Network", "nnet");
				if (pfad != null)
					speicherortZuErstellen.setText(pfad);
			}
		});
		JButton btnErstellenZuErstellen = new JButton("Erstellen");

		final JCheckBox chckbxAutosaveZuErstellen = new JCheckBox("Autosave");
		chckbxAutosaveZuErstellen
				.setToolTipText("Automatische Speicherung jede Minute");

		btnErstellenZuErstellen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] layerStr = layerZuErstellen.getText().split(" ");
					int[] layer = new int[layerStr.length];
					for (int i = 0; i < layerStr.length; i++)
						layer[i] = Integer.parseInt(layerStr[i]);
					if (chckbxTrainierenZuErstellen.isSelected()) {
						new GenNetz(
								new Genom(
										layer,
										Double.parseDouble(learningRateZuErstellen
												.getText()),
										Double.parseDouble(momentumZuErstellen
												.getText()),
										"".equals(maxIterationsZuErstellen
												.getText()) ? Integer.MAX_VALUE
												: Integer
														.parseInt(maxIterationsZuErstellen
																.getText()),
										(TransferFunctionType) transferFunktion
												.getSelectedItem(),
										new SnakeTrainer(
												spielaufzeichnungZuErstellen
														.getText(), layer[0],
												layer[layer.length - 1])),
								Integer.parseInt(maxTrainingZuErstellen
										.getText()) * 1000,
								speicherortZuErstellen.getText(),
								chckbxAutosaveZuErstellen.isSelected());
					} else {
						new GenNetz(new Genom(layer,
								Double.parseDouble(learningRateZuErstellen
										.getText()), Double
										.parseDouble(momentumZuErstellen
												.getText()), Integer.MAX_VALUE,
								(TransferFunctionType) transferFunktion
										.getSelectedItem(), null), 0,
								speicherortZuErstellen.getText(), false);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Fehlerhafte Eingabe",
							"Fehler", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}

		});

		JLabel lblAlsGenomSpeichern = new JLabel("Als Genom speichern:");

		JLabel label_5 = new JLabel("Speicherort:");

		speicherortZuAlsGenomSpeichern = new JTextField();
		speicherortZuAlsGenomSpeichern.setColumns(10);

		final JButton btnSpeicherortZuAlsGenomSpeichern = new JButton("...");
		btnSpeicherortZuAlsGenomSpeichern
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String pfad = chooseFileToSave("Geonom", "gen");
						if (pfad != null)
							speicherortZuAlsGenomSpeichern.setText(pfad);
					}
				});

		final JButton btnAlsGenomSpeichern = new JButton("Als Genom speichern");
		btnAlsGenomSpeichern.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] layerStr = layerZuErstellen.getText().split(" ");
				int[] layer = new int[layerStr.length];
				for (int i = 0; i < layerStr.length; i++)
					layer[i] = Integer.parseInt(layerStr[i]);
				try {
					MyUtils.writeFile(
							new Genom(
									layer,
									Double.parseDouble(learningRateZuErstellen
											.getText()),
									Double.parseDouble(momentumZuErstellen
											.getText()),
									"".equals(maxIterationsZuErstellen
											.getText()) ? Integer.MAX_VALUE
											: Integer
													.parseInt(maxIterationsZuErstellen
															.getText()),
									TransferFunctionType
											.valueOf(transferFunktion.getName()),
									new SnakeTrainer(
											spielaufzeichnungZuErstellen
													.getText(), layer[0],
											layer[layer.length - 1]))
									.toString(),
							btnSpeicherortZuAlsGenomSpeichern.getText());
				} catch (Exception e) {

				}
			}
		});
		chckbxTrainierenZuErstellen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean active = chckbxTrainierenZuErstellen.isSelected();
				maxIterationsZuErstellen.setEnabled(active);
				// learningRateZuErstellen.setEnabled(active);
				// momentumZuErstellen.setEnabled(active);
				speicherortZuAlsGenomSpeichern.setEnabled(active);
				maxTrainingZuErstellen.setEnabled(active);
				spielaufzeichnungZuErstellen.setEnabled(active);
				btnSpielaufzeichnungZuErstellen.setEnabled(active);
				btnSpeicherortZuAlsGenomSpeichern.setEnabled(active);
				btnAlsGenomSpeichern.setEnabled(active);
			}

		});
		btnLadenZuErstellen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String genStr = MyUtils.readFile(genomZuErstellen.getText());
					Genom gen = new Genom(genStr, null);
					transferFunktion.setSelectedItem(gen.getTransferFunktion());
					StringBuffer layerStr = new StringBuffer();
					int[] layers = gen.getLayers();
					for (int lay : layers) {
						layerStr.append(lay);
						layerStr.append(" ");
					}
					layerZuErstellen.setText(layerStr.toString());
					// chckbxTrainierenZuErstellen.setSelected(true);
					maxIterationsZuErstellen.setText(gen.getMaxIterations()
							+ "");
					learningRateZuErstellen.setText(gen.getLearningRate() + "");
					momentumZuErstellen.setText(gen.getMomentum() + "");
					speicherortZuAlsGenomSpeichern.setText(genomZuErstellen
							.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Fehler beim Lesen des Genoms", "Fehler",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}

		});

		GroupLayout gl_erstellen = new GroupLayout(erstellen);
		gl_erstellen
				.setHorizontalGroup(gl_erstellen
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_erstellen
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_erstellen
																		.createSequentialGroup()
																		.addComponent(
																				chckbxTrainierenZuErstellen)
																		.addGap(63)
																		.addComponent(
																				chckbxAutosaveZuErstellen)
																		.addContainerGap())
														.addGroup(
																gl_erstellen
																		.createSequentialGroup()
																		.addGroup(
																				gl_erstellen
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_erstellen
																										.createSequentialGroup()
																										.addGroup(
																												gl_erstellen
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblGenomAuswhlen,
																																GroupLayout.DEFAULT_SIZE,
																																599,
																																Short.MAX_VALUE)
																														.addComponent(
																																genomZuErstellen,
																																GroupLayout.DEFAULT_SIZE,
																																599,
																																Short.MAX_VALUE))
																										.addGap(6)
																										.addComponent(
																												btnGenomZuErstellen,
																												GroupLayout.PREFERRED_SIZE,
																												27,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								btnLadenZuErstellen,
																								Alignment.TRAILING,
																								GroupLayout.PREFERRED_SIZE,
																								0,
																								Short.MAX_VALUE)
																						.addGroup(
																								gl_erstellen
																										.createSequentialGroup()
																										.addGroup(
																												gl_erstellen
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblLayer)
																														.addComponent(
																																lblTransferfunktion)
																														.addComponent(
																																lblLearningrate)
																														.addComponent(
																																lblMomentum)
																														.addComponent(
																																lblSpeicherort))
																										.addGap(48)
																										.addGroup(
																												gl_erstellen
																														.createParallelGroup(
																																Alignment.TRAILING)
																														.addGroup(
																																gl_erstellen
																																		.createSequentialGroup()
																																		.addGroup(
																																				gl_erstellen
																																						.createParallelGroup(
																																								Alignment.LEADING)
																																						.addComponent(
																																								transferFunktion,
																																								0,
																																								486,
																																								Short.MAX_VALUE)
																																						.addComponent(
																																								layerZuErstellen,
																																								GroupLayout.DEFAULT_SIZE,
																																								486,
																																								Short.MAX_VALUE)
																																						.addGroup(
																																								gl_erstellen
																																										.createSequentialGroup()
																																										.addGroup(
																																												gl_erstellen
																																														.createParallelGroup(
																																																Alignment.LEADING)
																																														.addComponent(
																																																learningRateZuErstellen,
																																																Alignment.TRAILING,
																																																GroupLayout.DEFAULT_SIZE,
																																																486,
																																																Short.MAX_VALUE)
																																														.addComponent(
																																																momentumZuErstellen,
																																																GroupLayout.DEFAULT_SIZE,
																																																486,
																																																Short.MAX_VALUE))
																																										.addPreferredGap(
																																												ComponentPlacement.RELATED)))
																																		.addGap(0))
																														.addGroup(
																																gl_erstellen
																																		.createSequentialGroup()
																																		.addComponent(
																																				speicherortZuErstellen,
																																				GroupLayout.DEFAULT_SIZE,
																																				453,
																																				Short.MAX_VALUE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				btnSpeicherortZuErstellen,
																																				GroupLayout.PREFERRED_SIZE,
																																				27,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)))))
																		.addGap(12))
														.addGroup(
																gl_erstellen
																		.createSequentialGroup()
																		.addGroup(
																				gl_erstellen
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnErstellenZuErstellen,
																								GroupLayout.DEFAULT_SIZE,
																								632,
																								Short.MAX_VALUE)
																						.addGroup(
																								gl_erstellen
																										.createSequentialGroup()
																										.addComponent(
																												label_5)
																										.addGap(76)
																										.addComponent(
																												speicherortZuAlsGenomSpeichern,
																												GroupLayout.DEFAULT_SIZE,
																												453,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnSpeicherortZuAlsGenomSpeichern,
																												GroupLayout.PREFERRED_SIZE,
																												27,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								btnAlsGenomSpeichern,
																								GroupLayout.DEFAULT_SIZE,
																								632,
																								Short.MAX_VALUE)
																						.addGroup(
																								gl_erstellen
																										.createSequentialGroup()
																										.addGroup(
																												gl_erstellen
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblMaximaleIterationen)
																														.addGroup(
																																gl_erstellen
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblSpielaufzeichnung)
																																		.addGap(39)
																																		.addComponent(
																																				spielaufzeichnungZuErstellen,
																																				GroupLayout.DEFAULT_SIZE,
																																				453,
																																				Short.MAX_VALUE)))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnSpielaufzeichnungZuErstellen,
																												GroupLayout.PREFERRED_SIZE,
																												27,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_erstellen
																										.createSequentialGroup()
																										.addComponent(
																												lblMaximaleTrainingszeit)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_erstellen
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																maxIterationsZuErstellen,
																																Alignment.TRAILING,
																																GroupLayout.DEFAULT_SIZE,
																																486,
																																Short.MAX_VALUE)
																														.addComponent(
																																maxTrainingZuErstellen,
																																GroupLayout.DEFAULT_SIZE,
																																486,
																																Short.MAX_VALUE))))
																		.addContainerGap())
														.addGroup(
																gl_erstellen
																		.createSequentialGroup()
																		.addComponent(
																				lblAlsGenomSpeichern)
																		.addContainerGap(
																				520,
																				Short.MAX_VALUE)))));
		gl_erstellen
				.setVerticalGroup(gl_erstellen
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_erstellen
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblGenomAuswhlen)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																genomZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGenomZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnLadenZuErstellen)
										.addGap(12)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																transferFunktion,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblTransferfunktion))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblLayer)
														.addComponent(
																layerZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(6)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																learningRateZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblLearningrate))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																momentumZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblMomentum))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblSpeicherort)
														.addComponent(
																speicherortZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnSpeicherortZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																chckbxTrainierenZuErstellen)
														.addComponent(
																chckbxAutosaveZuErstellen))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																maxTrainingZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblMaximaleTrainingszeit))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblMaximaleIterationen)
														.addComponent(
																maxIterationsZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblSpielaufzeichnung)
														.addComponent(
																spielaufzeichnungZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnSpielaufzeichnungZuErstellen,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnErstellenZuErstellen,
												GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblAlsGenomSpeichern)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_erstellen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																speicherortZuAlsGenomSpeichern,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_5)
														.addComponent(
																btnSpeicherortZuAlsGenomSpeichern,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnAlsGenomSpeichern)
										.addGap(30)));
		erstellen.setLayout(gl_erstellen);

		JPanel trainieren = new JPanel();
		tabbedPane.addTab("Netz trainieren", null, trainieren, null);

		netzwerkZuTrainieren = new JTextField();
		netzwerkZuTrainieren.setColumns(10);

		JLabel lblNetzwerkAuswhlen_1 = new JLabel("Netzwerk ausw\u00E4hlen:");

		JButton btnNetzwerkZuTrainieren = new JButton("...");
		btnNetzwerkZuTrainieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfad = chooseFileToOpen("Neuronal Network", "nnet");
				if (pfad != null)
					netzwerkZuTrainieren.setText(pfad);
			}
		});

		JLabel label = new JLabel("Spielaufzeichnung:");

		spielaufzeichnungZuTrainieren = new JTextField();
		spielaufzeichnungZuTrainieren.setColumns(10);

		JButton btnSpielaufzeichnungZuTrainieren = new JButton("...");
		btnSpielaufzeichnungZuTrainieren
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String pfad = chooseFileToOpen("SnakeLog", "slog");
						if (pfad != null)
							spielaufzeichnungZuTrainieren.setText(pfad);
					}
				});

		JLabel label_3 = new JLabel("Maximale Trainingszeit:");

		JLabel label_4 = new JLabel("Maximale Iterationen:");

		maxIterationsZuTrainieren = new JTextField();
		maxIterationsZuTrainieren.setToolTipText("Als Ganzzahl");
		maxIterationsZuTrainieren.setColumns(10);

		maxTrainingZuTrainieren = new JTextField();
		maxTrainingZuTrainieren.setToolTipText("In Sekunden");
		maxTrainingZuTrainieren.setColumns(10);

		JCheckBox chckbxAutosaveZuTrainieren = new JCheckBox("Autosave");
		chckbxAutosaveZuTrainieren
				.setToolTipText("Automatische Speicherung jede Minute");

		JButton btnStartZuTrainieren = new JButton("Start");
		btnStartZuTrainieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final NeuralNetwork<?> nn = NeuralNetwork
						.createFromFile(netzwerkZuTrainieren.getText());
				((MomentumBackpropagation) nn.getLearningRule())
						.setMaxIterations("".equals(maxIterationsZuTrainieren
								.getText()) ? Integer.MAX_VALUE : Integer
								.parseInt(maxIterationsZuTrainieren.getText()));
				try {
					new SnakeTrainer(spielaufzeichnungZuTrainieren.getText(),
							nn.getInputsCount(), nn.getOutputsCount()).train(
							nn,
							Integer.parseInt(maxTrainingZuTrainieren.getText()) * 1000);
					nn.save(netzwerkZuTrainieren.getText());
					JOptionPane.showMessageDialog(null,
							"Das Netz wurde erfolgreich trainiert",
							"Training erfolgreich",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fehlerhafte Eingabe",
							"Fehler", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Datei nicht gefunden",
							"Fehler", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"Fehler beim Lesen der Datei", "Fehler",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Training Set passt nicht zum Netz", "Fehler",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}
		});

		GroupLayout gl_trainieren = new GroupLayout(trainieren);
		gl_trainieren
				.setHorizontalGroup(gl_trainieren
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_trainieren
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_trainieren
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_trainieren
																		.createParallelGroup(
																				Alignment.LEADING)
																		.addGroup(
																				Alignment.TRAILING,
																				gl_trainieren
																						.createSequentialGroup()
																						.addGroup(
																								gl_trainieren
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												lblNetzwerkAuswhlen_1)
																										.addComponent(
																												label))
																						.addGap(24)
																						.addGroup(
																								gl_trainieren
																										.createParallelGroup(
																												Alignment.TRAILING)
																										.addGroup(
																												gl_trainieren
																														.createSequentialGroup()
																														.addComponent(
																																netzwerkZuTrainieren,
																																GroupLayout.DEFAULT_SIZE,
																																116,
																																Short.MAX_VALUE)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																btnNetzwerkZuTrainieren,
																																GroupLayout.PREFERRED_SIZE,
																																27,
																																GroupLayout.PREFERRED_SIZE))
																										.addGroup(
																												gl_trainieren
																														.createSequentialGroup()
																														.addComponent(
																																spielaufzeichnungZuTrainieren,
																																GroupLayout.DEFAULT_SIZE,
																																116,
																																Short.MAX_VALUE)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																btnSpielaufzeichnungZuTrainieren,
																																GroupLayout.PREFERRED_SIZE,
																																27,
																																GroupLayout.PREFERRED_SIZE)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)))
																						.addGap(12))
																		.addGroup(
																				gl_trainieren
																						.createSequentialGroup()
																						.addGroup(
																								gl_trainieren
																										.createParallelGroup(
																												Alignment.TRAILING)
																										.addGroup(
																												gl_trainieren
																														.createSequentialGroup()
																														.addComponent(
																																label_4)
																														.addGap(24)
																														.addComponent(
																																maxIterationsZuTrainieren,
																																GroupLayout.DEFAULT_SIZE,
																																149,
																																Short.MAX_VALUE))
																										.addGroup(
																												gl_trainieren
																														.createSequentialGroup()
																														.addComponent(
																																label_3)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																maxTrainingZuTrainieren,
																																GroupLayout.DEFAULT_SIZE,
																																149,
																																Short.MAX_VALUE)))
																						.addContainerGap()))
														.addGroup(
																Alignment.TRAILING,
																gl_trainieren
																		.createSequentialGroup()
																		.addComponent(
																				chckbxAutosaveZuTrainieren)
																		.addContainerGap())
														.addGroup(
																gl_trainieren
																		.createSequentialGroup()
																		.addComponent(
																				btnStartZuTrainieren,
																				GroupLayout.DEFAULT_SIZE,
																				295,
																				Short.MAX_VALUE)
																		.addContainerGap()))));
		gl_trainieren
				.setVerticalGroup(gl_trainieren
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_trainieren
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_trainieren
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																netzwerkZuTrainieren,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNetzwerkZuTrainieren,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblNetzwerkAuswhlen_1))
										.addGap(7)
										.addGroup(
												gl_trainieren
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnSpielaufzeichnungZuTrainieren,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																spielaufzeichnungZuTrainieren,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_trainieren
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_3)
														.addComponent(
																maxTrainingZuTrainieren,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(5)
										.addGroup(
												gl_trainieren
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																maxIterationsZuTrainieren,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_4))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(
												chckbxAutosaveZuTrainieren)
										.addGap(18)
										.addComponent(btnStartZuTrainieren,
												GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(270, Short.MAX_VALUE)));
		trainieren.setLayout(gl_trainieren);

		JPanel spielen = new JPanel();
		tabbedPane.addTab("Spielen lassen", null, spielen, null);

		netzwerkZuSpielen = new JTextField();
		netzwerkZuSpielen.setColumns(10);

		JLabel lblNetzwerkAuswhlen = new JLabel("Netzwerk ausw\u00E4hlen:");

		JButton btnNetzwerkZuSpielen = new JButton("...");
		btnNetzwerkZuSpielen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfad = chooseFileToOpen("Neuronal Network", "nnet");
				if (pfad != null)
					netzwerkZuSpielen.setText(pfad);
			}
		});

		JButton btnStartZuSpielen = new JButton("Start");
		btnStartZuSpielen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SnakePlayer(NeuralNetwork.createFromFile(netzwerkZuSpielen
						.getText())).start();
				;
			}
		});
		GroupLayout gl_spielen = new GroupLayout(spielen);
		gl_spielen
				.setHorizontalGroup(gl_spielen
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_spielen
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_spielen
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_spielen
																		.createSequentialGroup()
																		.addGroup(
																				gl_spielen
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								netzwerkZuSpielen,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								310,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblNetzwerkAuswhlen,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								310,
																								Short.MAX_VALUE))
																		.addGap(6)
																		.addComponent(
																				btnNetzwerkZuSpielen,
																				GroupLayout.PREFERRED_SIZE,
																				27,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																btnStartZuSpielen,
																GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_spielen
				.setVerticalGroup(gl_spielen
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_spielen
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblNetzwerkAuswhlen)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_spielen
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																netzwerkZuSpielen,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNetzwerkZuSpielen,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(btnStartZuSpielen,
												GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(404, Short.MAX_VALUE)));
		spielen.setLayout(gl_spielen);

		JPanel evolution = new JPanel();
		tabbedPane.addTab("Evolution", null, evolution, null);

		speicherortZuEvolution = new JTextField();
		speicherortZuEvolution.setColumns(10);

		JLabel lblSpeicherortAuswhlen = new JLabel(
				"Speicherort ausw\u00E4hlen:");

		JButton btnSpeicherortZuEvolution = new JButton("...");
		btnSpeicherortZuEvolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfad = chooseFolder();
				if (pfad != null)
					speicherortZuEvolution.setText(pfad);
			}
		});

		JButton btnStartZuEvolution = new JButton("Start");
		btnStartZuEvolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final PrintStream stdout = System.out;
				System.setOut(consoleStreamforTextArea);
				try {
					final EVKontrolle eK = new EVKontrolle(Integer
							.parseInt(individuenZuEvolution.getText()), Integer
							.parseInt(testsZuEvolution.getText()), Integer
							.parseInt(maxTrainingZuEvolution.getText()) * 1000,
							speicherortZuEvolution.getText(),
							spielaufzeichnungZuEvolution.getText());
					new Thread() {
						public void run() {
							eK.entwickle(Integer
									.parseInt(generationenZuEvolution.getText()));
							System.setOut(stdout);
						}
					}.start();
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JLabel label_6 = new JLabel("Maximale Trainingszeit:");

		maxTrainingZuEvolution = new JTextField();
		maxTrainingZuEvolution.setToolTipText("In Sekunden");
		maxTrainingZuEvolution.setColumns(10);

		JLabel lblAnzahlDerIndividuen = new JLabel("Anzahl der Individuen:");

		individuenZuEvolution = new JTextField();
		individuenZuEvolution
				.setToolTipText("Als Ganzzahl. Am Besten ein Vielfaches Ihrer CPU-Kerne");
		individuenZuEvolution.setColumns(10);

		JLabel lblAnzahlDerTests = new JLabel("Anzahl der Tests:");

		testsZuEvolution = new JTextField();
		testsZuEvolution
				.setToolTipText("Als Ganzzahl. Eine sehr hohe Anzahl ist hier Vorteilhaft. Ca. 10000");
		testsZuEvolution.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel lblAnzahlDerGenerationen = new JLabel("Anzahl der Generationen:");

		generationenZuEvolution = new JTextField();
		generationenZuEvolution.setToolTipText("Als Ganzzahl");
		generationenZuEvolution.setColumns(10);

		JLabel lblNewLabel = new JLabel("Spielaufzeichnung:");

		spielaufzeichnungZuEvolution = new JTextField();
		spielaufzeichnungZuEvolution.setColumns(10);

		JButton btnSpielaufzeichnungZuEvolution = new JButton("...");
		btnSpielaufzeichnungZuEvolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfad = chooseFileToOpen("Snakelog", "slog");
				if (pfad != null)
					spielaufzeichnungZuEvolution.setText(pfad);

			}
		});
		GroupLayout gl_evolution = new GroupLayout(evolution);
		gl_evolution
				.setHorizontalGroup(gl_evolution
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								Alignment.LEADING,
								gl_evolution
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_evolution
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																295,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																gl_evolution
																		.createSequentialGroup()
																		.addGroup(
																				gl_evolution
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_evolution
																										.createSequentialGroup()
																										.addComponent(
																												lblNewLabel)
																										.addGap(39)
																										.addComponent(
																												spielaufzeichnungZuEvolution,
																												GroupLayout.DEFAULT_SIZE,
																												116,
																												Short.MAX_VALUE))
																						.addComponent(
																								speicherortZuEvolution,
																								GroupLayout.DEFAULT_SIZE,
																								262,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblSpeicherortAuswhlen,
																								GroupLayout.DEFAULT_SIZE,
																								262,
																								Short.MAX_VALUE))
																		.addGap(6)
																		.addGroup(
																				gl_evolution
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnSpielaufzeichnungZuEvolution,
																								GroupLayout.PREFERRED_SIZE,
																								27,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnSpeicherortZuEvolution,
																								GroupLayout.PREFERRED_SIZE,
																								27,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_evolution
																		.createSequentialGroup()
																		.addGroup(
																				gl_evolution
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								label_6,
																								GroupLayout.PREFERRED_SIZE,
																								134,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblAnzahlDerIndividuen)
																						.addComponent(
																								lblAnzahlDerTests)
																						.addComponent(
																								lblAnzahlDerGenerationen))
																		.addGap(4)
																		.addGroup(
																				gl_evolution
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								individuenZuEvolution,
																								GroupLayout.DEFAULT_SIZE,
																								149,
																								Short.MAX_VALUE)
																						.addComponent(
																								testsZuEvolution,
																								GroupLayout.DEFAULT_SIZE,
																								149,
																								Short.MAX_VALUE)
																						.addComponent(
																								generationenZuEvolution,
																								GroupLayout.DEFAULT_SIZE,
																								149,
																								Short.MAX_VALUE)
																						.addComponent(
																								maxTrainingZuEvolution,
																								GroupLayout.DEFAULT_SIZE,
																								149,
																								Short.MAX_VALUE)))
														.addComponent(
																btnStartZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_evolution
				.setVerticalGroup(gl_evolution
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_evolution
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblSpeicherortAuswhlen)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_evolution
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																speicherortZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnSpeicherortZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_evolution
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																spielaufzeichnungZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnSpielaufzeichnungZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_evolution
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_evolution
																		.createSequentialGroup()
																		.addGap(8)
																		.addComponent(
																				label_6))
														.addGroup(
																gl_evolution
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				maxTrainingZuEvolution,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_evolution
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblAnzahlDerIndividuen)
														.addComponent(
																individuenZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_evolution
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblAnzahlDerTests)
														.addComponent(
																testsZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_evolution
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblAnzahlDerGenerationen)
														.addComponent(
																generationenZuEvolution,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnStartZuEvolution,
												GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 227,
												Short.MAX_VALUE)
										.addContainerGap()));

		ausgabeKonsole = new JTextArea();
		scrollPane.setViewportView(ausgabeKonsole);
		ausgabeKonsole.setEditable(false);
		DefaultCaret caret = (DefaultCaret) ausgabeKonsole.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		evolution.setLayout(gl_evolution);
	}

	public String chooseFileToSave(String type, String extention) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(type,
				extention);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (chooser.getSelectedFile().isDirectory())
				return null;
			String name = chooser.getSelectedFile().getAbsolutePath();
			return name.endsWith(extention) ? name : (name + "." + extention);
		} else {
			return null;
		}
	}

	public String chooseFileToOpen(String type, String extention) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(type,
				extention);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}

	}

	public String chooseFolder() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		int returnVal = chooser.showOpenDialog(chooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}
	}

	private void updateFeld(String str) {
		ausgabeKonsole.append(str + "\n");
	}
}
