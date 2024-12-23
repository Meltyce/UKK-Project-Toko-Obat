package main;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class f_penjualan extends javax.swing.JFrame {

    String Tanggal;
    private DefaultTableModel model;

    public f_penjualan() {
        initComponents();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-YYYY");

        model = new DefaultTableModel();
        tbl_jual.setModel(model);

        model.addColumn("ID PENJUALAN");
        model.addColumn("ID PELANGGAN");
        model.addColumn("NAMA PELANGGAN");
        model.addColumn("ID PEMBAYARAN");
        model.addColumn("KODE OBAT");
        model.addColumn("NAMA OBAT");
        model.addColumn("ID USER");
        model.addColumn("NAMA USER");
        model.addColumn("TANGGAL");
        model.addColumn("HARGA");
        model.addColumn("JUMLAH");
        model.addColumn("TOTAL");
        tanggal.setText(s.format(date));
        txt_totalbayar.setText("0");
        txt_bayar.setText("0");
        txt_kembalian.setText("0");
        txt_idpelanggan.requestFocus();
        pelanggan();
        obat();
        user();
        pembayaran();
        autonumber();

    }

    public void totalBiaya() {
        int jumlahBaris = tbl_jual.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(tbl_jual.getValueAt(i, 10).toString());
            hargaBarang = Integer.parseInt(tbl_jual.getValueAt(i, 9).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txt_totalbayar.setText(String.valueOf(totalBiaya));
        txt_tampil.setText("Rp" + totalBiaya + ",00");
    }

    public void autonumber() {
        try {
            Connection conn = koneksi.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "Select * from penjualan ORDER BY no_faktur DESC";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String noFaktur = rs.getString("no_faktur").substring(2);
                String TR = "" + (Integer.parseInt(noFaktur) + 1);
                String Nol = "";

                if (TR.length() == 1) {
                    Nol = "000";
                } else if (TR.length() == 2) {
                    Nol = "00";
                } else if (TR.length() == 3) {
                    Nol = "0";
                } else if (TR.length() == 4) {
                    Nol = "";
                }
                txt_idpenjualan.setText("TR" + Nol + TR);
            } else {
                txt_idpenjualan.setText("TR0001");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Autonumbererror");
        }
    }

    public void Tampildata() {
        DefaultTableModel model = (DefaultTableModel) tbl_jual.getModel();
        model.addRow(new Object[]{
            txt_idpenjualan.getText(),
            txt_idpelanggan.getText(),
            combo_pelanggan.getSelectedItem(),
            txt_idpembayaran.getText(),
            txt_kodeobat.getText(),
            combo_obat.getSelectedItem(),
            txt_iduser.getText(),
            combo_user.getSelectedItem(),
            tanggal.getText(),
            txt_harga.getText(),
            txt_jumlah.getText(),
            txt_totalbayar.getText()

        });
    }

    public void kosong() {
        DefaultTableModel model = (DefaultTableModel) tbl_jual.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public void utama() {
        txt_idpenjualan.setText("");
        txt_idpelanggan.setText("");
        combo_pelanggan.setSelectedItem("");
        tanggal.setText("");
        txt_iduser.setText("");
        combo_user.setSelectedItem("");
        txt_idpembayaran.setText("");
        combo_bayar.setSelectedItem("");
        combo_obat.setSelectedItem("");
        txt_kodeobat.setText("");
        txt_jumlah.setText("");
        autonumber();
    }

    public void clear() {
        txt_idpelanggan.setText("");
        combo_pelanggan.setSelectedItem("");
        txt_totalbayar.setText("");
        txt_bayar.setText("");
        txt_kembalian.setText("");
        txt_tampil.setText("0");
    }

    public void clear2() {
        txt_kodeobat.setText("");
        combo_obat.setSelectedItem("");
        txt_harga.setText("");
        txt_jumlah.setText("");
    }

    public void tambah_transaksi() {
        int jumlah, harga, total;

        jumlah = Integer.valueOf(txt_jumlah.getText());
        harga = Integer.valueOf(txt_harga.getText());
        total = jumlah * harga;

        txt_totalbayar.setText(String.valueOf(total));

        Tampildata();
        totalBiaya();
        clear2();
        txt_kodeobat.requestFocus();
    }

    public void user() {
        try {
            String sql = "SELECT * from user ";
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String list_user = rs.getString("nama_user");
                combo_user.addItem(list_user);

            }
        } catch (Exception e) {
        }
    }

    public void pelanggan() {
        try {
            String sql = "SELECT * from pelanggan";
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String list_pelanggan = rs.getString("nama_pelanggan");
                combo_pelanggan.addItem(list_pelanggan);

            }
        } catch (Exception e) {
        }
    }

    public void obat() {
        try {
            String sql = "SELECT * from obat";
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String list_obat = rs.getString("nama_obat");
                combo_obat.addItem(list_obat);

            }
        } catch (Exception e) {
        }
    }

    public void pembayaran() {
        try {
            String sql = "SELECT * from pembayaran";
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String list_bayar = rs.getString("nama_pembayaran");
                combo_bayar.addItem(list_bayar);

            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_idpenjualan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_idpelanggan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tanggal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_jual = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_totalbayar = new javax.swing.JTextField();
        txt_bayar = new javax.swing.JTextField();
        txt_kembalian = new javax.swing.JTextField();
        txt_idpembayaran = new javax.swing.JTextField();
        txt_kodeobat = new javax.swing.JTextField();
        txt_jumlah = new javax.swing.JTextField();
        combo_pelanggan = new javax.swing.JComboBox<>();
        combo_obat = new javax.swing.JComboBox<>();
        txt_tampil = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_iduser = new javax.swing.JTextField();
        combo_user = new javax.swing.JComboBox<>();
        combo_bayar = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Penjualan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(544, 544, 544)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("ID PENJUALAN");

        txt_idpenjualan.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_idpenjualan.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("ID PELANGGAN");

        txt_idpelanggan.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_idpelanggan.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("NAMA PELANGGAN");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("DATE");

        tanggal.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tanggal.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("ID PEMBAYARAN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("KODE OBAT");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("NAMA OBAT");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("JUMLAH");

        tbl_jual.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tbl_jual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jScrollPane1.setViewportView(tbl_jual);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("TOTAL BAYAR");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("BAYAR");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("KEMBALIAN");

        txt_totalbayar.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        txt_bayar.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });

        txt_kembalian.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        txt_kodeobat.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_kodeobat.setEnabled(false);

        txt_jumlah.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });

        combo_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_pelangganActionPerformed(evt);
            }
        });

        combo_obat.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        combo_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_obatActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("ID USER");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("NAMA USER");

        txt_iduser.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        txt_iduser.setEnabled(false);

        combo_user.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        combo_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_userActionPerformed(evt);
            }
        });

        combo_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_bayarActionPerformed(evt);
            }
        });

        jLabel15.setText("HARGA");

        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        jButton1.setText("REPORT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jMenuBar1.setMargin(new java.awt.Insets(3, 3, 3, 3));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(870, 50));

        jMenu1.setText("Logout");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Admin");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_idpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_idpenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(combo_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tanggal)
                                            .addComponent(txt_iduser))))))
                        .addGap(165, 165, 165)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel9)
                            .addComponent(jLabel14))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_kodeobat)
                            .addComponent(txt_jumlah)
                            .addComponent(combo_obat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo_user, 0, 282, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(combo_bayar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_idpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(94, 94, 94)
                        .addComponent(jLabel15)
                        .addGap(27, 27, 27)
                        .addComponent(txt_harga, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_totalbayar, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txt_bayar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_idpenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_idpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_idpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_kodeobat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(combo_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_obat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(txt_iduser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_user, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_totalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(41, 41, 41)))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_pelangganActionPerformed
        try {
            Object kode = combo_pelanggan.getSelectedItem();

            String sql = "SELECT id_pelanggan from pelanggan where nama_pelanggan='" + kode + "'";
            //System.out.println(sql);
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String pelanggan = rs.getString("id_pelanggan");
                txt_idpelanggan.setText(pelanggan);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_combo_pelangganActionPerformed

    private void combo_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_obatActionPerformed
        try {
            Object kode = combo_obat.getSelectedItem();

            String sql = "SELECT kode_obat,harga from obat where nama_obat='" + kode + "'";
            //System.out.println(sql);
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String obat = rs.getString("kode_obat");
                txt_kodeobat.setText(obat);
                String harga = rs.getString("harga");
                txt_harga.setText(harga);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_combo_obatActionPerformed

    private void combo_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_userActionPerformed
        try {
            Object kode = combo_user.getSelectedItem();

            String sql = "SELECT id_user from user where nama_user='" + kode + "'";
            //System.out.println(sql);
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String user = rs.getString("id_user");
                txt_iduser.setText(user);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_combo_userActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        tambah_transaksi();
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        tambah_transaksi();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_jual.getModel();
        int row = tbl_jual.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txt_bayar.setText("0");
        txt_kembalian.setText("0");
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        int total, bayar, kembalian;

        total = Integer.valueOf(txt_totalbayar.getText());
        bayar = Integer.valueOf(txt_bayar.getText());

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang Anda Tidak Cukup");

        } else {
            kembalian = bayar - total;
            txt_kembalian.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void combo_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_bayarActionPerformed
        try {
            Object kode = combo_bayar.getSelectedItem();

            String sql = "SELECT id_pembayaran from pembayaran where nama_pembayaran='" + kode + "'";
            //System.out.println(sql);
            Connection conn = koneksi.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                String bayar = rs.getString("id_pembayaran");
                txt_idpembayaran.setText(bayar);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_combo_bayarActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbl_jual.getModel();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-YYYY");
        try {
            Connection conn = koneksi.getConnection();
            int baris = tbl_jual.getRowCount();

            for (int i = 0; i < baris; i++) {
                PreparedStatement stmt = conn.prepareStatement("INSERT into penjualan (no_faktur, id_pelanggan, id_pembayaran, kode_obat,  id_user, tanggal, harga, jumlah, total ) VALUES('"
                        + tbl_jual.getValueAt(i, 0)
                        + "','" + tbl_jual.getValueAt(i, 1)
                        + "','" + tbl_jual.getValueAt(i, 3)
                        + "','" + tbl_jual.getValueAt(i, 4)
                        + "','" + tbl_jual.getValueAt(i, 6)
                        + "','" + tbl_jual.getValueAt(i, 8)
                        + "','" + tbl_jual.getValueAt(i, 9)
                        + "','" + tbl_jual.getValueAt(i, 10)
                        + "','" + tbl_jual.getValueAt(i, 11) + "')");
                stmt.executeUpdate();
                stmt.close();
            }

        } catch (Exception e) {
            System.out.println("System detail error");
        }
        clear();
        autonumber();
        kosong();
        utama();
        txt_tampil.setText("RP 0");
        tanggal.setText(s.format(date));
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseWheelMoved

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        f_loginuser fa = new f_loginuser();
        fa.show();
        this.dispose();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        f_aksesadmin fa = new f_aksesadmin();
        fa.show();
        this.dispose();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            File namafile = new File("src/report/reportobat.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, koneksi.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new f_penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox<String> combo_bayar;
    private javax.swing.JComboBox<String> combo_obat;
    private javax.swing.JComboBox<String> combo_pelanggan;
    private javax.swing.JComboBox<String> combo_user;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField tanggal;
    private javax.swing.JTable tbl_jual;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_idpelanggan;
    private javax.swing.JTextField txt_idpembayaran;
    private javax.swing.JTextField txt_idpenjualan;
    private javax.swing.JTextField txt_iduser;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_kodeobat;
    private javax.swing.JTextField txt_tampil;
    private javax.swing.JTextField txt_totalbayar;
    // End of variables declaration//GEN-END:variables
}
