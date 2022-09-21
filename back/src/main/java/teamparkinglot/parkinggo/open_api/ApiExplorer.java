package teamparkinglot.parkinggo.open_api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamparkinglot.parkinggo.parking.entity.Parking;
import teamparkinglot.parkinggo.secret.SecretCode;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ApiExplorer {

    private final SecretCode secretCode;

    public void searchFromApi(String where) throws IOException {

        System.out.println("where = " + secretCode.getParkingOpenApiEncodingKey());

        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_prkplce_info_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + secretCode.getParkingOpenApiEncodingKey()); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML/JSON 여부*/
//        urlBuilder.append("&" + URLEncoder.encode("prkplceNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*주차장관리번호*/
        urlBuilder.append("&" + URLEncoder.encode("prkplceNm","UTF-8") + "=" + URLEncoder.encode(where, "UTF-8")); /*주차장명*/
//        urlBuilder.append("&" + URLEncoder.encode("prkplceSe","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주차장구분*/
//        urlBuilder.append("&" + URLEncoder.encode("prkplceType","UTF-8") + "=" + URLEncoder.encode("노상", "UTF-8")); /*주차장유형*/
//        urlBuilder.append("&" + URLEncoder.encode("rdnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소재지도로명주소*/
//        urlBuilder.append("&" + URLEncoder.encode("lnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소재지지번주소*/
//        urlBuilder.append("&" + URLEncoder.encode("prkcmprt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주차구획수*/
//        urlBuilder.append("&" + URLEncoder.encode("feedingSe","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*급지구분*/
//        urlBuilder.append("&" + URLEncoder.encode("enforceSe","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*부제시행구분*/
//        urlBuilder.append("&" + URLEncoder.encode("operDay","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*운영요일*/
//        urlBuilder.append("&" + URLEncoder.encode("weekdayOperOpenHhmm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*평일운영시작시각*/
//        urlBuilder.append("&" + URLEncoder.encode("weekdayOperColseHhmm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*평일운영종료시각*/
//        urlBuilder.append("&" + URLEncoder.encode("satOperOperOpenHhmm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*토요일운영시작시각*/
//        urlBuilder.append("&" + URLEncoder.encode("satOperCloseHhmm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*토요일운영종료시각*/
//        urlBuilder.append("&" + URLEncoder.encode("holidayOperOpenHhmm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공휴일운영시작시각*/
//        urlBuilder.append("&" + URLEncoder.encode("holidayCloseOpenHhmm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*공휴일운영종료시각*/
//        urlBuilder.append("&" + URLEncoder.encode("parkingchrgeInfo","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*요금정보*/
//        urlBuilder.append("&" + URLEncoder.encode("basicTime","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주차기본시간*/
//        urlBuilder.append("&" + URLEncoder.encode("basicCharge","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*주차기본요금*/
//        urlBuilder.append("&" + URLEncoder.encode("addUnitTime","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*추가단위시간*/
//        urlBuilder.append("&" + URLEncoder.encode("addUnitCharge","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*추가단위요금*/
//        urlBuilder.append("&" + URLEncoder.encode("dayCmmtktAdjTime","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*1일주차권요금적용시간*/
//        urlBuilder.append("&" + URLEncoder.encode("dayCmmtkt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*1일주차권요금*/
//        urlBuilder.append("&" + URLEncoder.encode("monthCmmtkt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*월정기권요금*/
//        urlBuilder.append("&" + URLEncoder.encode("metpay","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*결제방법*/
//        urlBuilder.append("&" + URLEncoder.encode("spcmnt","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*특기사항*/
//        urlBuilder.append("&" + URLEncoder.encode("institutionNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*관리기관명*/
//        urlBuilder.append("&" + URLEncoder.encode("phoneNumber","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*전화번호*/
//        urlBuilder.append("&" + URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*위도*/
//        urlBuilder.append("&" + URLEncoder.encode("longitude","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*경도*/
//        urlBuilder.append("&" + URLEncoder.encode("referenceDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*데이터기준일자*/
//        urlBuilder.append("&" + URLEncoder.encode("instt_code","UTF-8") + "=" + URLEncoder.encode(where, "UTF-8")); /*제공기관코드*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

    }

}
