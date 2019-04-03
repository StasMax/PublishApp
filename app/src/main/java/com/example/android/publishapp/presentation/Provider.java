package com.example.android.publishapp.presentation;

import com.example.android.publishapp.data.model.PublishModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Provider {


    public List<PublishModel> listProvider(){
        List<PublishModel>list = new ArrayList<>();
        String[] categories = new String[]{"Toyota", "Mazda", "Fiat"};
        String[] tags = new String[]{"Corola", "cx-5", "Albea"};
        String header = "This is cars of Japanese";
        String description = "The most popular cars in Russian Federation";
        List<String>pictures = new ArrayList<>();
        pictures.add("https://im0-tub-ru.yandex.net/i?id=2134ea3527e1bbae522c720e066805e2-sr&n=13");
        pictures.add("https://im0-tub-ru.yandex.net/i?id=dec26fcdd38fd56454f36fa2320237da-sr&n=13");
        pictures.add("http://carfor.ru/upload/autocat/big/1_4258_0.jpg");
        Map<String, String>link = new HashMap<>();
        link.put("http://developer.alexanderklimov.ru/android/java/hashmap.php", "HashMap");
        link.put("http://developer.alexanderklimov.ru/android/library/picasso.php", "Picasso");
        list.add(new PublishModel(categories, tags, header, description, pictures, link, null, 1));
        list.add(new PublishModel(categories, tags, header, description, pictures, link, null, 0));
        list.add(new PublishModel(categories, tags, header, description, pictures, link, null, 1));
        list.add(new PublishModel(categories, tags, header, description, pictures, link, null, 0));



        return list;
    }
}
