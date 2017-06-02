package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/6/2.
 */

public class Utility {

    public static boolean handleProvinceResponse(String response)
    {
        if(!TextUtils.isEmpty(response))
            try{
                JSONArray allProvinces =new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++)
                {
                    JSONObject provinceObject =allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvincename(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        return false;
    }
    public static boolean handleCityResponse(String response,int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allcities=new JSONArray(response);
                for (int i=0;i<allcities.length();i++)
                {
                    JSONObject cityObject =allcities.getJSONObject(i);
                    City city=new City();
                    city.setCityname(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){e.printStackTrace();}
        }
        return false;
    }

    public static boolean handlecountyresponse(String response,int cityid)
    {
        if(!TextUtils.isEmpty(response))
        {
            try{
                JSONArray allcounties=new JSONArray(response);
                for (int i=0;i<allcounties.length();i++)
                {
                    JSONObject countyobject=allcounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyname(countyobject.getString("name"));
                    county.setWeatherid(countyobject.getString("weather_id"));
                    county.setCityid(cityid);
                    county.save();
                }
                return true;
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return  false;
    }

}

