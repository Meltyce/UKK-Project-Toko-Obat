package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class f_dataobat extends javax.swing.JFrame {

    private DefaultTableModel DftTblModel_obat;
    private String SQL;

    public f_dataobat() {
        initComponents();
        kategori();
        TampilData();
    }

    private void kategori() {
        try {
            String sql = "SELECT * from kategori";
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String list_kategori = rs.getString("nama_kategori");
                combo_kategori.addItem(list_kategori);

            }
        } catch (Exception e) {
        }
    }

    public void TampilData() {
        DftTblModel_obat = new DefaultTableModel();
        DftTblModel_obat.addColumn("KODE OBAT");
        DftTblModel_obat.addColumn("KATEGORI");
        DftTblModel_obat.addColumn("NAMA OBAT");
        DftTblModel_obat.addColumn("HARGA");
        DftTblModel_obat.addColumn("KETERANGAN");
        DftTblModel_obat.addColumn("STOK");
        DftTblModel_obat.addColumn("EXP");
        tbl_obat.setModel(DftTblModel_obat);
        Connection conn = koneksi.getConnection();

        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "Select \n"
                    + "obat.kode_obat,\n"
                    + "kategori.nama_kategori,\n"
                    + "obat.nama_obat,\n"
                    + "obat.harga,\n"
                    + "obat.keterangan,\n"
                    + "obat.stok,\n"
                    + "obat.exp\n"
                    + "FROM obat INNER JOIN kategori on obat.id_kategori = kategori.id_kategori";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel_obat.addRow(new Object[]{
                    res.getString("kode_obat"),
                    res.getString("nama_kategori"),
                    res.getString("nama_obat"),
                    res.getString("harga"),
                    res.getString("keterangan"),
                    res.getString("stok"),
                    res.getString("exp")
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_kategori = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        combo_kategori = new javax.swing.JComboBox<>();
        txt_keterangan = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        btn_create = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_obat = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        txt_date = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("FORM DATA OBAT");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("ID OBAT");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("ID KATEGORI");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("NAMA OBAT");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("HARGA");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("KETERANGAN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("STOK");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("EXP");

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_id.setEnabled(false);

        txt_kategori.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kategoriActionPerformed(evt);
            }
        });

        txt_nama.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        txt_harga.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        combo_kategori.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        combo_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_kategoriActionPerformed(evt);
            }
        });

        txt_keterangan.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        txt_stok.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        btn_create.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_create.setText("Create");
        btn_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createActionPerformed(evt);
            }
        });

        btn_edit.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_clear.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        tbl_obat.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tbl_obat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        tbl_obat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_obatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_obat);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Cari Data");

        txt_cari.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        btn_cari.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btn_cari.setText("Cari Data");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        txt_date.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        jMenuBar2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jMenuBar2.setMargin(new java.awt.Insets(3, 3, 3, 3));
        jMenuBar2.setPreferredSize(new java.awt.Dimension(870, 50));

        jMenu3.setText("Logout");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu3);

        jMenu2.setText("Admin");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(91, 91, 91)
                                                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txt_nama)
                                                        .addComponent(txt_harga)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(combo_kategori, 0, 145, Short.MAX_VALUE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(txt_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel7)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(0, 224, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(btn_create, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txt_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_create, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        txt_kategori.setText("");
        txt_nama.setText("");
        txt_harga.setText("");
        txt_keterangan.setText("");
        txt_stok.setText("");
        txt_date.setText("");
    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kategoriActionPerformed

    private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
        DateFormat dateformat = new SimpleDateFormat("DD-MM-YYYY");
        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement stmt = conn.prepareStatement("insert into obat( id_kategori, nama_obat, harga, keterangan, stok, exp)value(?,?,?,?,?,?)");
            stmt.setString(1, txt_kategori.getText());
            stmt.setString(2, txt_nama.getText());
            stmt.setString(3, txt_harga.getText());
            stmt.setString(4, txt_keterangan.getText());
            stmt.setString(5, txt_stok.getText());
            stmt.setString(6, txt_date.getText());
            stmt.executeUpdate();
            JOptionPane.showConfirmDialog(null, "Penyimpanan Data Berhasil", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            TampilData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_createActionPerformed

    private void combo_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_kategoriActionPerformed
        try {
            Object kode = combo_kategori.getSelectedItem();

            String sql = "SELECT id_kategori from kategori where nama_kategori='" + kode + "'";
            //System.out.println(sql);
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String level = rs.getString("id_kategori");
                txt_kategori.setText(level);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_combo_kategoriActionPerformed

    private void tbl_obatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_obatMouseClicked

        int baris = tbl_obat.rowAtPoint(evt.getPoint());
        String kode_obat = tbl_obat.getValueAt(baris, 0).toString();
        txt_id.setText(kode_obat);
        String kategori = tbl_obat.getValueAt(baris, 1).toString();
        combo_kategori.setSelectedItem(kategori);
        String nama = tbl_obat.getValueAt(baris, 2).toString();
        txt_nama.setText(nama);
        String harga = tbl_obat.getValueAt(baris, 3).toString();
        txt_harga.setText(harga);
        String keterangan = tbl_obat.getValueAt(baris, 4).toString();
        txt_keterangan.setText(keterangan);
        String stok = tbl_obat.getValueAt(baris, 5).toString();
        txt_stok.setText(stok);
        String exp = tbl_obat.getValueAt(baris, 6).toString();
        txt_date.setText(exp);
    }//GEN-LAST:event_tbl_obatMouseClicked

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        try {
            Connection conn = koneksi.getConnection();
            PreparedStatement stmt = conn.prepareStatement("update obat set id_kategori=?, nama_obat=?, harga=?, keterangan=?, stok=?, exp=? where kode_obat=?");
            stmt.setString(1, txt_kategori.getText());
            stmt.setString(2, txt_nama.getText());
            stmt.setString(3, txt_harga.getText());
            stmt.setString(4, txt_keterangan.getText());
            stmt.setString(5, txt_stok.getText());
            stmt.setString(6, txt_date.getText());
            stmt.setString(7, txt_id.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dirubah");
            TampilData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        Connection conn = koneksi.getConnection();
        int confirm;
        confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data Tersebut?", "Pesan", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("delete from obat where kode_obat='" + txt_id.getText() + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                TampilData();
                txt_id.setText("");
                txt_kategori.setText("");
                txt_nama.setText("");
                txt_harga.setText("");
                txt_keterangan.setText("");
                txt_stok.setText("");
                txt_date.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"
                        + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        DftTblModel_obat = new DefaultTableModel();
        DftTblModel_obat.addColumn("KODE OBAT");
        DftTblModel_obat.addColumn("KATEGORI");
        DftTblModel_obat.addColumn("NAMA OBAT");
        DftTblModel_obat.addColumn("HARGA");
        DftTblModel_obat.addColumn("KETERANGAN");
        DftTblModel_obat.addColumn("STOK");
        DftTblModel_obat.addColumn("EXP");
        tbl_obat.setModel(DftTblModel_obat);
        Connection conn = koneksi.getConnection();

        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "Select \n"
                    + "obat.kode_obat,\n"
                    + "kategori.nama_kategori,\n"
                    + "obat.nama_obat,\n"
                    + "obat.harga,\n"
                    + "obat.keterangan,\n"
                    + "obat.stok,\n"
                    + "obat.exp\n"
                    + "FROM obat INNER JOIN kategori on obat.id_kategori = kategori.id_kategori WHERE nama_obat LIKE '%" + txt_cari.getText() + "%'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                DftTblModel_obat.addRow(new Object[]{
                    res.getString("kode_obat"),
                    res.getString("nama_kategori"),
                    res.getString("nama_obat"),
                    res.getString("harga"),
                    res.getString("keterangan"),
                    res.getString("stok"),
                    res.getString("exp")
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        f_loginuser fa = new f_loginuser();
        fa.show();
        this.dispose();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        f_aksesadmin fa = new f_aksesadmin();
        fa.show();
        this.dispose();
    }//GEN-LAST:event_jMenu2MouseClicked

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(f_dataobat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(f_dataobat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(f_dataobat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(f_dataobat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new f_dataobat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_create;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JComboBox<String> combo_kategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_obat;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_date;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_kategori;
    private javax.swing.JTextField txt_keterangan;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
