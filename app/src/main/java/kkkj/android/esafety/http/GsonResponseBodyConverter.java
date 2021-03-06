/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kkkj.android.esafety.http;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static kkkj.android.esafety.mvpInterface.MvpModel.RESPONSE_OK;
import static okhttp3.internal.Util.UTF_8;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Reader reader = null;
        JsonReader jsonReader= null;
        InputStream inputStream = null;
        try {
            //ResultResponse 只解析result字段
            ESafetyResponse resultResponse = gson.fromJson(response, ESafetyResponse.class);
            if (resultResponse.getState() == RESPONSE_OK) {
                //result==0表示成功返回，继续用本来的Model类解析
                MediaType contentType = value.contentType();
                Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;

                inputStream = new ByteArrayInputStream(response.getBytes());
                reader = new InputStreamReader(inputStream, charset);
                jsonReader = gson.newJsonReader(reader);

                return adapter.read(jsonReader);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                throw new ResultException(resultResponse.getState(), resultResponse.getMsg());
            }
        } finally {
            value.close();
            if(inputStream!=null)
            {
                inputStream.close();
            }
            if(jsonReader!=null)
            {
                jsonReader.close();
            }
            if(reader!=null)
            {
                reader.close();
            }
        }
    }
}
