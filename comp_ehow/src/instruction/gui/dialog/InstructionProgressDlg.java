/*
 * Copyright (c) 2009-10 Daniel Nyga
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Technische Universiteit Eindhoven nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
*/
package instruction.gui.dialog;

import instruction.importer.InstructionProgressListener;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class InstructionProgressDlg extends JDialog implements InstructionProgressListener {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JProgressBar progressBar = null;
	private JButton btnCancel = null;
	private JScrollPane jScrollPane = null;
	private JTextPane txtConsole = null;

	/**
	 * @param owner
	 */
	public InstructionProgressDlg ( Frame owner ) {

		super( owner, "", true );
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setSize( 364, 260 );
		this.setContentPane( getJPanel() );
		this.setTitle( "Transformation Progress" );
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {

		if ( jPanel == null ) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.weighty = 1.0;
			gridBagConstraints11.insets = new Insets( 0, 10, 0, 10 );
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.insets = new Insets( 10, 10, 10, 10 );
			gridBagConstraints21.gridy = 3;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets( 10, 10, 10, 10 );
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets( 10, 10, 10, 10 );
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText( "Please wait while the Howto is transformed..." );
			jPanel = new JPanel();
			jPanel.setLayout( new GridBagLayout() );
			jPanel.add( jLabel, gridBagConstraints );
			jPanel.add( getProgressBar(), gridBagConstraints1 );
			jPanel.add( getBtnCancel(), gridBagConstraints21 );
			jPanel.add( getJScrollPane(), gridBagConstraints11 );
		}
		return jPanel;
	}

	/**
	 * This method initializes progressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	private JProgressBar getProgressBar() {

		if ( progressBar == null ) {
			progressBar = new JProgressBar();
			progressBar.setPreferredSize( new Dimension( 148, 20 ) );
		}
		return progressBar;
	}

	/**
	 * This method initializes btnCancel
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnCancel() {

		if ( btnCancel == null ) {
			btnCancel = new JButton();
			btnCancel.setText( "Cancel" );
		}
		return btnCancel;
	}

	public void setProgress( int percent ) {

		progressBar.setValue( percent );
	}

	public void progressNotification( int percentage, String msg ) {

		if ( percentage != - 1 )
			progressBar.setValue( percentage );

		String txt = txtConsole.getText();
		txtConsole.setText( txt + msg );
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {

				int max = jScrollPane.getVerticalScrollBar().getMaximum();

				jScrollPane.getVerticalScrollBar().setValue( max );
			}
		} );
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {

		if ( jScrollPane == null ) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ) );
			jScrollPane.setViewportView( getTxtConsole() );

		}
		return jScrollPane;
	}

	/**
	 * This method initializes txtConsole
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextPane getTxtConsole() {

		if ( txtConsole == null ) {
			txtConsole = new JTextPane();
			txtConsole.setFont( new Font( "DialogInput", Font.PLAIN, 12 ) );
			txtConsole.setEditable( false );
			txtConsole.setBorder( BorderFactory.createEmptyBorder() );
		}
		return txtConsole;
	}

} // @jve:decl-index=0:visual-constraint="301,35"
