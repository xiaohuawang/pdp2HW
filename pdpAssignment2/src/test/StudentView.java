package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import test.TestJDBC;

public class StudentView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();

		JLabel jlnum = new JLabel("number");
		final JTextField jtfnum = new JTextField(8);
		jf.add(jlnum);
		jf.add(jtfnum);

		JLabel jlname = new JLabel("name");
		final JTextField jtfname = new JTextField(8);
		jf.add(jlname);
		jf.add(jtfname);

		JLabel jlchinese = new JLabel("chinese");
		final JTextField jtfchinese = new JTextField(8);
		jf.add(jlchinese);
		jf.add(jtfchinese);

		JLabel jlmaths = new JLabel("maths");
		final JTextField jtfmaths = new JTextField(8);
		jf.add(jlmaths);
		jf.add(jtfmaths);

		JLabel jlenglish = new JLabel("english");
		final JTextField jtfenglish = new JTextField(8);
		jf.add(jlenglish);
		jf.add(jtfenglish);

		JLabel jlzhonghe = new JLabel("zhonghe");
		final JTextField jtfzhonghe = new JTextField(8);
		jf.add(jlzhonghe);
		jf.add(jtfzhonghe);

		JButton jbadd = new JButton("add");
		jf.add(jbadd);

		jbadd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String numStr = jtfnum.getText();
				int num = Integer.parseInt(numStr);
				String name = jtfname.getText();
				String chinese = jtfchinese.getText();
				String maths = jtfmaths.getText();
				String english = jtfenglish.getText();
				String zhonghe = jtfzhonghe.getText();

//				TestJDBC test = new TestJDBC();
//				test.insertIntoTable(num, name, chinese, maths, english, zhonghe);
			}
		});

		String[] titles = { "student_num", "student_name", "chinese", "maths", "english", "zhonghe" };
		final DefaultTableModel dtm = new DefaultTableModel(titles, 0);
		// final DefaultTableModel dtm = new DefaultTableModel();
		final JTable jt = new JTable(dtm);
		JScrollPane jsp = new JScrollPane(jt);

		jf.add(jsp);

		final JButton jbquery = new JButton("search");
		jf.add(jbquery);

		jbquery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Object[]> list = new ArrayList<Object[]>();
				dtm.setRowCount(0);
				String querynum = jtfnum.getText();
				String queryname = jtfname.getText();
				System.out.println("querynum= " + querynum);
//				TestJDBC test = new TestJDBC();
//				list = test.getSelectSQL(queryname, querynum);
				System.out.println("row.length= " + list.size());
				for (int i = 0; i < list.size(); i++) {
					dtm.addRow(list.get(i));
				}

			}

		});

		// String titles= {}

		final JButton jbdelete = new JButton("delete");
		jbdelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StringBuilder sb = new StringBuilder();
				List<String> list = new ArrayList<String>();
				int[] st = jt.getSelectedRows();
				sb.append("delete from student where student_id in (");
				int count = 0;
				for (int s : st) {
					list.add((String) jt.getValueAt(s, 0));
				}

				sb.append(")");

				int i = JOptionPane.showConfirmDialog(null, "you want to delete all?");
				if (i == 0) {
//					TestJDBC test = new TestJDBC();
//					test.deleteAll(sb.toString());
				}
			}

		});

		JButton jbupdate = new JButton("update");
		jf.add(jbupdate);
		jbdelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("update come");
				System.out.println("first row= "+jt.getValueAt(jt.getSelectedRow(), 0));
				
				jtfnum.setText((String) jt.getValueAt(jt.getSelectedRow(), 0));
				jtfname.setText((String) jt.getValueAt(jt.getSelectedRow(), 1));
				jtfchinese.setText((String) jt.getValueAt(jt.getSelectedRow(), 2));
				jtfmaths.setText((String) jt.getValueAt(jt.getSelectedRow(), 3));
				jtfenglish.setText((String) jt.getValueAt(jt.getSelectedRow(), 4));
				jtfzhonghe.setText((String) jt.getValueAt(jt.getSelectedRow(), 5));
				jtfnum.setEnabled(false);
			}

		});

		JButton jbdo = new JButton("save");
		jf.add(jbdo);
		jbdo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String num = jtfnum.getText();
				String name = jtfname.getText();
				String chinese = jtfchinese.getText();
				String maths = jtfmaths.getText();
				String english = jtfenglish.getText();
				String zhonghe = jtfzhonghe.getText();

//				TestJDBC test = new TestJDBC();
				String sql = "update student set student_name=" + name + ", chinese_score=" + chinese + ", math_score= "
						+ maths + ", english_score= " + english + ", zhonghe_score= " + zhonghe + " where student_id="
						+ num;
				// String sql="update student set student name="+name+",chinse_score="+chinese;
//				test.updateSQL(sql);
				jbquery.doClick();
			}

		});

		jf.setSize(1000, 800);
		jf.setVisible(true);
		jf.setLayout(new FlowLayout());
	}

}
