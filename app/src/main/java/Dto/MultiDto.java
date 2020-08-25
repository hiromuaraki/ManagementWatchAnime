package Dto;

import java.io.Serializable;
import java.util.List;

public class MultiDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String char1;
    private boolean flg;
    private int num1;
    private List<Integer> mList;

    public MultiDto() {
        this.char1 = null;
        this.flg = false;
        this.num1 = 0;
        this.mList = mList;
    }

    public int getMlist() {
        return mList.size();
    }

    public void setMlist(List<Integer> mList) {
        this.mList = mList;
    }

    public String getChar1() {
        return char1;
    }

    public boolean getisFlg() {
        return flg;
    }

    public int getNum1() {
        return num1;
    }

    public void setChar1(String char1) {
        this.char1 = char1;
    }

    public void setisFlg(boolean flg) {
        this.flg = flg;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }
}
