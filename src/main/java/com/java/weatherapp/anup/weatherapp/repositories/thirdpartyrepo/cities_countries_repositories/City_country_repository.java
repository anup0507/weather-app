package com.java.weatherapp.anup.weatherapp.repositories.thirdpartyrepo.cities_countries_repositories;

import com.java.weatherapp.anup.weatherapp.models.weatherapimodels.country_citymodels.Country_City_masterDataModel;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class City_country_repository {
    private HashMap<String,HashMap<String,Country_City_masterDataModel>> countrydata=new HashMap<>();
    private HashSet<String> unique_countries=new HashSet<>();
    private String fileName="";
    public List<String> getAllCountriesList(String filePath) throws FileNotFoundException, ParseException, JSONException {
        if(fileName.equals(""))
        {

            List<Country_City_masterDataModel> data=readDataFromFile(filePath);
            saveDataJsonInRepo(data);
            fileName=filePath;
        }
        List<String> list=new ArrayList<>(unique_countries);
        return list;
    }
    public List<String> getALlCitiesListFromCountry(String filePath,String countryName) throws FileNotFoundException, ParseException, JSONException {
        if(fileName.equals(""))
        {
            List<Country_City_masterDataModel> data=readDataFromFile(filePath);
            saveDataJsonInRepo(data);
            fileName=filePath;
        }
        HashMap<String,Country_City_masterDataModel> temp=countrydata.get(countryName);
        List<String> list=new ArrayList<>();
        for(Map.Entry<String,Country_City_masterDataModel> entry:temp.entrySet())
        {
            list.add(entry.getKey());
        }
        return list;
    }

    private void saveDataJsonInRepo(List<Country_City_masterDataModel> data)  {
        for(int i=0;i<data.size();i++)
        {

            if(!countrydata.containsKey(data.get(i).getCountry()))
            {
                HashMap<String,Country_City_masterDataModel> temp=new HashMap<>();
                temp.put(data.get(i).getSubcountry(),data.get(i));
                countrydata.put(data.get(i).getCountry(),temp);
            }
            else {
                HashMap<String,Country_City_masterDataModel> temp=countrydata.get(data.get(i).getCountry());
                if(temp.containsKey(data.get(i).getSubcountry()))
                    continue;
                temp.put(data.get(i).getSubcountry(),data.get(i));

            }
            unique_countries.add(data.get(i).getCountry());

        }
    }


    private List<Country_City_masterDataModel> readDataFromFile(String fileName) throws FileNotFoundException, ParseException {
        FileReader reader = new FileReader(fileName);
        JSONParser parser = new JSONParser(reader);
        try {
            Object obj = parser.parse();
            if (obj instanceof List) {
                List<Country_City_masterDataModel> data=new ArrayList<>();
                List<Object> list = (List<Object>) obj;
                for(int i=0;i<list.size();i++)
                {
                    Country_City_masterDataModel cityMasterDataModel=new Country_City_masterDataModel();
                    cityMasterDataModel.setCountry((String) ((LinkedHashMap)list.get(i)).get("country"));
                    cityMasterDataModel.setName((String) ((LinkedHashMap)list.get(i)).get("country"));
                   // cityMasterDataModel.setGeonameid((String) ((LinkedHashMap)list.get(i)).get("geonameid"));
                    cityMasterDataModel.setSubcountry((String) ((LinkedHashMap)list.get(i)).get("subcountry"));
                    data.add(cityMasterDataModel);
                }
                return data;
            } else {
                throw new ParseException("Expected a JSON array in the file");
            }
        }
        catch(Exception ex)
        {
            throw new ParseException("Unable to parse the data file .Please try again later.");
        }



    }

}
