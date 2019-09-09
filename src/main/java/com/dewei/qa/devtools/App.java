package com.dewei.qa.devtools;

import com.dewei.qa.Api;
import com.dewei.qa.Wework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Created By weishikai
 * Date: 2019/9/9
 * Time: 13:28
 */
public class App extends Api {

    @Override
    public RequestSpecification getDefaultRequestSpecification() {
        RequestSpecification requestSpecification = super.getDefaultRequestSpecification();
        requestSpecification.queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON);

        requestSpecification.filter((req, res, ctx) -> {
            return ctx.next(req, res);
        });

        return requestSpecification;

    }

    public Response listApp() {
        return getResponseFromHar("app/app.har.json", ".*tid=67.*", null);
    }
}
