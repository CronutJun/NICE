package com.nicetcm.nibsplus.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class CResultSet implements Serializable {
    private final static Class<CResultSet> classObj = CResultSet.class;

    protected Vector m_vtData = new Vector(); // 여러 레코드 담을 객체
    protected Properties m_ptColumnName = new Properties(); // 레코드의 칼럼과 칼럼명을 담아
                                                            // 놓을 객체
    protected int m_nCurRecPos = 0; // 현재 레코드 위치
    protected int m_nColumnCnt = 0; // 레코드 컬럼 갯수
    protected int m_nRowCount = 0; // CResultSet의 Row Count;
    protected int m_nRowPerPage = 0; // Page Size;
    protected int m_nRequestedPage = 0; // Requested Page;
    protected int m_nTotalRowCount = 0; // 실제 ResultSet의 Row Count;
    protected String m_dbCharSet = null; // Database Character Set
    protected String m_localCharSet = null; // Local Character Set
    protected final boolean VERBOSE = true; // 화면출력여부 flag
    protected int ROW_TYPE = 1; // row type = 1 String, row type= 2 byte array
    protected String m_strTemp = null;
    protected String[] sRows = null; // 한로우.
    protected byte[][] bRows = null; // 한로우.

    public CResultSet() {
    }

    public CResultSet(ResultSet rs) throws SQLException {
        try {
            rs.last();
            m_nTotalRowCount = rs.getRow();
            rs.beforeFirst();

            // Make a Copy Set
            if (!makeSet(rs, 0, false)) {
                System.out.println("CResultSet : Make Set is false");
            }
        } catch (Exception e) {
            System.err
                    .println("CResultSet: Constructor : can't copy ResultSet .....");
            e.printStackTrace();
        }
    }

    public CResultSet(String ForHint, ResultSet rs) throws SQLException {
        try {
            // Make a Copy Set
            if (!makeSet(rs, 0, false)) {
                System.out.println("CResultSet : Make Set is false");
            }
        } catch (Exception e) {
            System.out
                    .println("CResultSet: Constructor : can't copy ResultSet .....");
            System.out.println("Error Cause : " + e);
            e.printStackTrace();
        }
    }

    public CResultSet(ResultSet rs, int totalRowCount) throws SQLException {
        try {
            m_nTotalRowCount = totalRowCount;
            // Make a Copy Set
            if (!makeSet(rs, 0, false)) {
                System.out.println("CResultSet : Make Set is false");
            }
        } catch (Exception e) {
            System.err
                    .println("CResultSet: Constructor : can't copy ResultSet .....");
            e.printStackTrace();
        }
    }

    public CResultSet(ResultSet rs, int nRequestedPage, int nPageSize,
            int totalRowCount) throws SQLException {
        try {
            // Get Total Row Count
            m_nTotalRowCount = totalRowCount;

            // Set Page Size and Requested Page
            m_nRowPerPage = nPageSize;
            m_nRequestedPage = nRequestedPage;

            // Make a Copy Set
            if (!makeSet(rs, 0, false)) {
                System.out.println("CResultSet : Make Set is false");
            }
        } catch (Exception e) {
            System.err
                    .println("CResultSet: Constructor : can't copy ResultSet .....");
            e.printStackTrace();
        }
    }

    public CResultSet(ResultSet rs, int nRequestedPage, int nPageSize)
            throws SQLException {
        try {

            // Get Total Row Count
            rs.last();
            m_nTotalRowCount = rs.getRow();
            rs.beforeFirst();

            // Set Page Size and Requested Page
            m_nRowPerPage = nPageSize;
            m_nRequestedPage = nRequestedPage;

            // Move Cursor to Requested Page
            if ((nRequestedPage - 1) * nPageSize < m_nTotalRowCount) {
                if (nRequestedPage > 1) {
                    rs.absolute((nRequestedPage - 1) * nPageSize);
                }
            }

            // Make a Copy Set
            if (((nRequestedPage - 1) * nPageSize < m_nTotalRowCount)
                    && !makeSet(rs, nPageSize, true)) {
                System.out.println("CResultSet : Make Set is false");
            }
        } catch (Exception e) {
            System.err
                    .println("CResultSet: Constructor : can't copy ResultSet .....");
            e.printStackTrace();
        }
    }

    public CResultSet(ResultSet rs, int nRequestedPage, int nPageSize,
            int totRowCount, boolean totalExist) throws SQLException {
        try {
            // Get Total Row Count
            if (!totalExist) {
                rs.last();
                m_nTotalRowCount = rs.getRow();
                rs.beforeFirst();
            } else {
                m_nTotalRowCount = totRowCount;
            }

            // Set Page Size and Requested Page
            m_nRowPerPage = nPageSize;
            m_nRequestedPage = nRequestedPage;

            // Move Cursor to Requested Page
            if ((nRequestedPage - 1) * nPageSize < m_nTotalRowCount) {
                if (nRequestedPage > 1) {
                    rs.absolute((nRequestedPage - 1) * nPageSize);
                }
            }

            // Make a Copy Set
            if (((nRequestedPage - 1) * nPageSize < m_nTotalRowCount)
                    && !makeSet(rs, nPageSize, true)) {
                System.out.println("CResultSet : Make Set is false");
            }
        } catch (Exception e) {
            System.err
                    .println("CResultSet: Constructor : can't copy ResultSet .....");
            e.printStackTrace();
        }
    }

    /**
     * CResultSet 오브젝트를 close시킴
     */
    public void close() {
        m_vtData.removeAllElements();
    }

    /**
     * 처음 레코드로 위치를 이동시키는 것
     *
     * @return true if so
     */
    public boolean first() {
        if (m_nRowCount <= 0)
            return false; // 레코드가 한 건도 없을 경우

        m_nCurRecPos = 1;
        return setRows();
    }

    /**
     * Cursor 를 BOF로 이동
     *
     * @return true if so
     */
    public void beforeFirst() {
        m_nCurRecPos = 0;
        sRows = null;
        bRows = null;
    }

    /**
     * Cursor 를 EOF로 이동
     *
     * @return true if so
     */
    public void afterLast() {
        m_nCurRecPos = m_nRowCount + 1;
        sRows = null;
        bRows = null;
    }

    /**
     * 다음 레코드로 위치를 이동시키는 것
     *
     * @return true if so
     */
    public boolean next() {
        if (m_nRowCount <= 0)
            return false; // 레코드가 한 건도 없을 경우
        m_nCurRecPos++; // Current Position을 증가시킨다.
        if (m_nCurRecPos <= m_nRowCount) {
            return setRows();
        }
        return false;
    }

    /**
     * 이전 레코드로 위치를 이동시키는 것 만약 레코드가 1개라도 있고 위치가 1에 있으면 0번 위치로 간다.
     *
     * @return true if so
     */
    public boolean previous() {
        if (m_nRowCount <= 0)
            return false; // 레코드가 한 건도 없을 경우
        if ((m_nCurRecPos < 0) || (m_nCurRecPos > m_nRowCount))
            return false; // 레코드위치가 오류인 경우

        if (m_nCurRecPos >= 1) {
            m_nCurRecPos--;
            return setRows();
        }
        return false;
    }

    /**
     * 마지막 레코드로 위치를 이동시키는 것
     *
     * @return true if so
     */
    public boolean last() {
        if (m_nRowCount <= 0)
            return false; // 레코드가 한 건도 없을 경우
        m_nCurRecPos = m_nRowCount;
        return setRows();
    }

    private boolean setRows() {
        if (m_nRowCount <= 0) {
            sRows = null;
            bRows = null;
            return false;
        }
        try {
            if (ROW_TYPE == 1)
                sRows = (String[]) m_vtData.elementAt(m_nCurRecPos - 1);
            else if (ROW_TYPE == 2)
                bRows = (byte[][]) m_vtData.elementAt(m_nCurRecPos - 1);
        } catch (Exception e) {
            System.err.println("CResultSet: setRows : can't set Rows.....");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * ResultSet으로부터 데이터를 읽어서 자료구조를 만든다.
     *
     * @param rs
     *            ResultSet
     * @return true if so
     * @see Types
     */
    public boolean makeSet(ResultSet rs, int nPageSize, boolean bIsPageSet)
            throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();

        if (rsmd == null)
            return false;

        int nFetchedSize = 0;
        String str = "";
        double d = 0;
        long l = 0;
        int n = 0;
        java.sql.Date dt = null;
        Time time = null;
        Timestamp timestamp = null;
        byte[] bt = null;
        ByteArrayInputStream bais = null;
        InputStream is = null;
        java.math.BigDecimal bd = null;

        int keyno = 1;
        try {
            m_nColumnCnt = rsmd.getColumnCount(); // 칼럼개수
            int colPos = 1; // 칼럼 타입을 파악할 컬럼 위치
            int nType = 0; // 칼럼 타입
            String strColName = ""; // 칼럼 명

            // Column Name 얻기.
            for (colPos = 1; colPos <= m_nColumnCnt; colPos++) {
                // 칼럼명을 얻어옴...
                strColName = rsmd.getColumnName(colPos);
                // 칼럼명과 칼럼 위치를 매핑
                if (!m_ptColumnName.containsKey(strColName.toLowerCase())) {
                    m_ptColumnName.setProperty(strColName.toLowerCase(), ""
                            + colPos);
                } else {
                    // 같은 컬럼명이 있을때.. 두번째 부터는 2부터 부친다. 즉... 이미 seq_no 라고 컬럼명이 있을때
                    // 다음 컬럼명은 seq_no2 가 된다.
                    while (true) {
                        keyno++;
                        if (!m_ptColumnName.containsKey(strColName
                                .toLowerCase()
                                + keyno)) {
                            m_ptColumnName.setProperty(strColName.toLowerCase()
                                    + keyno, "" + colPos);
                            keyno = 1;
                            break;
                        }
                    }
                }
            }

            // Data 얻기
            m_nRowCount = 0;
            nFetchedSize = 0;
            while (rs.next()) {
                nFetchedSize++;
                if (bIsPageSet && nFetchedSize > nPageSize)
                    break;

                if (ROW_TYPE == 1)
                    sRows = new String[m_nColumnCnt];
                else if (ROW_TYPE == 2)
                    bRows = new byte[m_nColumnCnt][];

                // Record 생성
                for (colPos = 1; colPos <= m_nColumnCnt; colPos++) {
                    nType = rsmd.getColumnType(colPos);
                    // System.out.println(nType + ": Types.CHAR = " +
                    // Types.CHAR);
                    try {
                        if (nType == -1) { // text type
                            str = rs.getString(colPos);
                            if (str == null) {
                                if (ROW_TYPE == 1)
                                    sRows[colPos - 1] = null;
                                else if (ROW_TYPE == 2)
                                    bRows[colPos - 1] = null;
                            } else {
                                if (ROW_TYPE == 1)
                                    sRows[colPos - 1] = str;
                                else if (ROW_TYPE == 2)
                                    bRows[colPos - 1] = str.getBytes();
                            }
                        } else {
                            switch (nType) {
                            case Types.VARBINARY:
                            case Types.BINARY:
                                if (VERBOSE)
                                    System.err
                                            .println("CResultSet : VARBINARY = "
                                                    + bt);
                                bt = rs.getBytes(colPos);
                                if (bt == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = new String(bt);
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = bt;
                                }
                                break;
                            case Types.CLOB:
                                Clob clob = rs.getClob(colPos);
                                Reader reader = clob.getCharacterStream();
                                if (reader == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    char[] ct = new char[(int) clob.length()];
                                    reader.read(ct);
                                    str = new String(ct);

                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = str;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = str.getBytes();
                                }
                                break;
                            case Types.LONGVARCHAR:
                                bais = (ByteArrayInputStream) rs
                                        .getAsciiStream(colPos);
                                if (bais == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    int cnt = bais.available();
                                    bt = new byte[cnt];
                                    bais.read(bt);
                                    str = new String(bt);
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = str;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = bt;
                                }
                                break;
                            case Types.VARCHAR:
                            case Types.DOUBLE:
                            case Types.REAL:
                            case Types.FLOAT:
                            case Types.DECIMAL:
                            case Types.SMALLINT:
                            case Types.TINYINT:
                            case Types.INTEGER:
                            case Types.BIGINT:
                            case Types.NUMERIC:
                                str = rs.getString(colPos);
                                if (str == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = str;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = str.getBytes();
                                }
                                break;
                            case Types.CHAR:
                                str = rs.getString(colPos);
                                if (str == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = str.trim();
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = str.trim()
                                                .getBytes();
                                }
                                break;
                            case Types.DATE:
                                dt = rs.getDate(colPos);
                                if (dt == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = dt.toString();
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = dt.toString()
                                                .getBytes();
                                }
                                break;
                            case Types.TIME:
                                time = rs.getTime(colPos);
                                if (time == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = time.toString();
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = time.toString()
                                                .getBytes();
                                }
                                break;
                            case Types.TIMESTAMP:
                                timestamp = rs.getTimestamp(colPos);
                                if (timestamp == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = timestamp
                                                .toString();
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = timestamp
                                                .toString().getBytes();
                                }
                                break;
                            default:
                                str = rs.getString(colPos);
                                if (str == null) {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = null;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = null;
                                } else {
                                    if (ROW_TYPE == 1)
                                        sRows[colPos - 1] = str;
                                    else if (ROW_TYPE == 2)
                                        bRows[colPos - 1] = str.getBytes();
                                }
                                break;
                            } // switch
                        } // end of else
                    } catch (Exception e) {
                        System.err.println("CResultSet : nType = " + nType);
                        System.err.println("CResultSet : colPos = " + colPos
                                + " 칼럼 catch error ===" + e);
                    }
                } // for - 칼럼갯수만큼 순환

                if (ROW_TYPE == 1)
                    m_vtData.addElement(sRows); // 레코드를 추가한다.
                else if (ROW_TYPE == 2)
                    m_vtData.addElement(bRows); // 레코드를 추가한다.
                m_nRowCount++; // 레코드 Count 증가

            } // while - 레코드 갯수만큼 순환
            if (VERBOSE)
//                System.out.println( "CResultSet : Record Count = " + getRowCount());
            return true;
        } // try finish
        catch (Exception e) {
            System.out.println("CResultSet : makeSet Error :::::::::::::::::");
            System.out.println("CResultSet : " + e.getMessage());
            System.out.println("Error Cause : " + e);
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 칼럼명으로 값을 얻고자 할 경우 해당 칼럼위치를 찾아내주는 기능을 수행 모두 소문자로 처리하여 비교 함.
     *
     * @param String
     *            columnName
     * @return int 칼럼의 위치
     */
    protected int findIndex(String columnName) {
        try {
            // default value로 1을 설정
            // TODO : default를 1로 하면 안될 것으로 판다. 에러를 발생해야 한다.
            Integer n = new Integer(m_ptColumnName.getProperty(columnName
                    .toLowerCase(), "1"));
            int nIndex = n.intValue();
            return nIndex - 1;
        } catch (Exception e) {
            System.err
                    .println("CResultSet : findIndex Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    public Properties getColumnNamesProperties() {
        return m_ptColumnName;
    }

    public String getColumnName(int columnIndex) {
        String strKey = "";
        for (Enumeration e = m_ptColumnName.keys(); e.hasMoreElements();) {
            strKey = (String) e.nextElement();
            if (m_ptColumnName.getProperty(strKey).equals(
                    String.valueOf(columnIndex))) {
                return strKey;
            }
        }
        return "";
    }

    public int getColumnSize() {
        return m_ptColumnName.size();
    }

    /**
     * byte 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return byte
     */
    public byte getByte(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return -1;

            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null)
                    return Byte.valueOf(sRows[columnIndex]).byteValue();
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null)
                        return Byte.valueOf(new String(bRows[columnIndex]))
                                .byteValue();
        } catch (Exception e) {
            System.err.println("CResultSet : getByte Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * byte 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return byte
     */
    public byte getByte(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getByte(findIndex(columnName));
    }

    /**
     * Time 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return Time
     */
    public java.sql.Time getTime(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return null;

            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null)
                    return Time.valueOf(sRows[columnIndex]);
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null)
                        return Time.valueOf(new String(bRows[columnIndex]));
        } catch (Exception e) {
            System.err.println("CResultSet : getTime Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Time 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return Time
     */
    public java.sql.Time getTime(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getTime(findIndex(columnName));
    }

    /**
     * Timestamp 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return Timestamp
     */
    public java.sql.Timestamp getTimestamp(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return null;

            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null)
                    return Timestamp.valueOf(sRows[columnIndex]);
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null)
                        return Timestamp
                                .valueOf(new String(bRows[columnIndex]));
        } catch (Exception e) {
            System.err
                    .println("CResultSet : getTimestamp Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Timestamp 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return Timestamp
     */
    public java.sql.Timestamp getTimestamp(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getTimestamp(findIndex(columnName));
    }

    /**
     * Date 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return Date
     */
    public java.sql.Date getDate(int columnIndex) {
        try {
            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null)
                    return java.sql.Date.valueOf(sRows[columnIndex]);
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null)
                        return java.sql.Date.valueOf(new String(
                                bRows[columnIndex]));
        } catch (Exception e) {
            System.err.println("CResultSet : getDate Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return Date
     */
    public java.sql.Date getDate(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getDate(findIndex(columnName));
    }

    /**
     * double 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return double
     */
    public double getDouble(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return -1;
            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null
                        && !sRows[columnIndex].equals(""))
                    return Double.valueOf(sRows[columnIndex]).doubleValue();
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null
                            && !new String(bRows[columnIndex]).equals(""))
                        return Double.valueOf(new String(bRows[columnIndex]))
                                .doubleValue();
            return 0;
        } catch (Exception e) {
            System.err
                    .println("CResultSet : getDouble Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * double 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return double
     */
    public double getDouble(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getDouble(findIndex(columnName));
    }

    /**
     * float 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return float
     */
    public float getFloat(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return -1;
            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null
                        && !sRows[columnIndex].equals(""))
                    return Float.valueOf(sRows[columnIndex]).floatValue();
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null
                            && !new String(bRows[columnIndex]).equals(""))
                        return Float.valueOf(new String(bRows[columnIndex]))
                                .floatValue();
            return 0;
        } catch (Exception e) {
            System.err.println("CResultSet : getFloat Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * float 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return float
     */
    public float getFloat(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getFloat(findIndex(columnName));
    }

    /**
     * int 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return int
     */
    public int getInt(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return -1;

            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null
                        && !sRows[columnIndex].equals(""))
                    return Integer.valueOf(sRows[columnIndex]).intValue();
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null
                            && !new String(bRows[columnIndex]).equals(""))
                        return Integer.valueOf(new String(bRows[columnIndex]))
                                .intValue();
            return 0;
        } catch (Exception e) {
            System.err.println("CResultSet : getInt Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * int 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return int
     */
    public int getInt(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getInt(findIndex(columnName));
    }

    /**
     * long 타입 칼럼을 Column Index를 이용하여 얻는다.
     *
     * @param int Column Index
     * @return long
     */
    public long getLong(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return -1;

            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null
                        && !sRows[columnIndex].equals(""))
                    return Long.valueOf(sRows[columnIndex]).longValue();
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null
                            && !new String(bRows[columnIndex]).equals(""))
                        return Long.valueOf(new String(bRows[columnIndex]))
                                .longValue();
            return 0;
        } catch (Exception e) {
            System.err.println("CResultSet : getLong Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * long 타입 칼럼을 Column Name을 이용하여 얻는다.
     *
     * @param String
     *            Column Name
     * @return long
     */
    public long getLong(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getLong(findIndex(columnName));
    }

    /**
     * String 타입 칼럼을 Column Index를 이용하여 얻는다. 이때 데이터베이스의 charater set을 local
     * chracter set으로 변환한다.
     *
     * @param int Column Index
     * @return string
     */
    public String getString(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return "<record position is 0>";

            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null
                        && !sRows[columnIndex].equals(""))
                    return sRows[columnIndex];
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null
                            && !new String(bRows[columnIndex]).equals(""))
                        return new String(bRows[columnIndex]);
        } catch (Exception e) {
            System.err
                    .println("CResultSet : getString Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    /**
     * <PRE>
     * String 타입 칼럼을 Column Index를 이용하여 얻는다.
     * 이때 데이터베이스의 charater set을 &quot;8859_1&quot; chracter set으로 변환한다.
     * </PRE>
     *
     * @param int Column Index
     * @return string
     */
    public String getUniString(int columnIndex) {
        try {
            if (m_nCurRecPos <= 0)
                return "<record position is 0>";

            if (ROW_TYPE == 1)
                if (sRows[columnIndex] != null
                        && !sRows[columnIndex].equals(""))
                    return sRows[columnIndex];
                else if (ROW_TYPE == 2)
                    if (bRows[columnIndex] != null
                            && !new String(bRows[columnIndex]).equals(""))
                        return new String(bRows[columnIndex]);
        } catch (Exception e) {
            System.err
                    .println("CResultSet : getUniString Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    /**
     * String 타입 칼럼을 Column Name을 이용하여 얻는다. 이때 데이터베이스의 Charater Set을 Local
     * Chracter Set으로 변환한다.
     *
     * @param String
     *            Column Name
     * @return string
     */
    public String getString(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getString(findIndex(columnName));
    }

    /**
     * String 타입 칼럼을 Column Name을 이용하여 얻는다. 이때 데이터베이스의 Charater Set을 System의
     * Chracter Set으로 변환한다.
     *
     * @param String
     *            Column Name
     * @return string
     */
    public String getUniString(String columnName) {
        // 칼럼의 위치로 찾아내는 메소드를 이용함...
        return getUniString(findIndex(columnName));
    }

    /**
     * 현재 ResultSet에 담긴 레코드 갯수를 반환시킴
     *
     * @return int record count
     */
    public int getRowCount() {
        try {
            return m_vtData.size();
        } catch (Exception e) {
            System.err
                    .println("CResultSet : getRowCount Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * SQL Statement내의 컬럼 갯수를 반환시킴
     *
     * @return int Columncount
     */
    public int getColumnCount() {
        try {
            return m_nColumnCnt;
        } catch (Exception e) {
            System.err
                    .println("CResultSet : getColumnCount Error :::::::::::::::::");
            System.err.println("CResultSet : " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 실제 검색된 전체 ResultSet 의 RowCount - 총 레코드 카운트를 얻음.
     *
     * @return int TotalRowCount
     */
    public int getTotalRowCount() {
        return m_nTotalRowCount;
    }

    /**
     * 요청된 Page No.
     *
     * @return int RequestedPage
     */
    public int getRequestedPage() {
        return m_nRequestedPage;
    }

    /**
     * Page Size
     *
     * @return int PageSize
     */
    public int getPageSize() {
        return m_nRowPerPage;
    }

    public void setPageSize(int n) {
        m_nRowPerPage = n;
    }

    public void setRequestedPage(int n) {
        m_nRequestedPage = n;
    }

    public void setTotalRowCount(int n) {
        m_nTotalRowCount = n;
    }

    public int getCurRowNum() {
        return m_nCurRecPos;
    }

    public void setCurRecPos(int n) {
        m_nCurRecPos = n;
    }

    public void setRowType(int it) {
        ROW_TYPE = it;
    }

    /**
     *set data
     */
    public void updateString(int curPos, String input) {
        ((Properties) m_vtData.elementAt(m_nCurRecPos - 1)).setProperty(curPos
                + "", input);
    }
}
