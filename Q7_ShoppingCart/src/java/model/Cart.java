/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyng
 */
public class Cart {
    private List<Item> items;
    public Cart(){
        items = new ArrayList<>();
    }
    public Cart(List<Item> items){
        this.items = items;
    }
    public Item getItemById(int id){
        for (Item item : items) {
            if(item.getProduct().getId() == id){
                return item;
            }
        }
        return null;
    }
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    //add to cart
    public void addItem(Item i){
        if(getItemById(i.getProduct().getId())!= null){
            Item t = getItemById(i.getProduct().getId());
            t.setQuantity(t.getQuantity() + i.getQuantity());
        }else{
            this.items.add(i);
        }
    }
    
    public void removeItem(int id){
        if(getItemById(id) != null){
            this.items.remove(getItemById(id));
        }
    }
    
    public double totalMoney(){
        double total = 0;
        for (Item item : items) {
            total += item.getProduct().getPrice()* item.getQuantity();
        }
        return total;
    }
    
    public List<Item> getItems(){
        return this.items;
    }
    
    private Product getProduct(List<Product> list, int id){
        for (Product product : list) {
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
    
    public Cart(String txt, List<Product> list){
        items = new ArrayList<>();
        if(txt!= null && txt.length() !=0){
            String[] s = txt.split(",");
            for (String string : s) {
                String[] n = string.split(":");
                int id = Integer.parseInt(n[0]);
                Product p = getProduct(list, id);
                Item t = new Item(p, Integer.parseInt(n[1]), p.getPrice());
                if(getItemById(t.getProduct().getId())!= null){
                    Item m = getItemById(t.getProduct().getId());
                    m.setQuantity(m.getQuantity() + t.getQuantity());
                }else{
                    items.add(t);
                }
            }
            
        }
    }
    
}
