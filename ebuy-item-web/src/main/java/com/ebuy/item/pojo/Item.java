package com.ebuy.item.pojo;

import com.ebuy.pojo.TbItem;

import java.text.SimpleDateFormat;

/**
 * Created by admin on 2017/12/29.
 */
public class Item extends TbItem {
    private String date;

    private String catName;

    public Item(TbItem tbItem) {
        //初始化属性
        this.setId(tbItem.getId());
        this.setTitle(tbItem.getTitle());
        this.setSellPoint(tbItem.getSellPoint());
        this.setPrice(tbItem.getPrice());
        this.setNum(tbItem.getNum());
        this.setBarcode(tbItem.getBarcode());
        this.setImage(tbItem.getImage());
        this.setCid(tbItem.getCid());
        this.setStatus(tbItem.getStatus());
        this.setCreated(tbItem.getCreated());
        this.setUpdated(tbItem.getUpdated());
        this.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.getCreated()));
    }

    public String[] getImages() {
        if (this.getImage()!=null && !"".equals(this.getImage())) {
            String image2 = this.getImage();
            String[] strings = image2.split(",");
            return strings;
        }
        return null;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

}
