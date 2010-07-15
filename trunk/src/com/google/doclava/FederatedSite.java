/*
 * Copyright (C) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.doclava;

import com.google.doclava.apicheck.ApiCheck;
import com.google.doclava.apicheck.ApiInfo;
import com.google.doclava.apicheck.ApiParseException;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A remote source of documentation that can be linked against. A site may be
 * linked to when an external library has packages, classes, and members
 * referenced or shared by the codebase for which documentation is being
 * generated.
 */
public final class FederatedSite {
  private final String name;
  private final URL baseUrl;
  private final ApiInfo apiInfo;
  
  public FederatedSite(String name, URL baseUrl) throws ApiParseException {
    this.name = name;
    this.baseUrl = baseUrl;
    
    try {
      URL xmlUrl = new URL(baseUrl + "/xml/current.xml");
      this.apiInfo = new ApiCheck().parseApi(xmlUrl);
    } catch (MalformedURLException e) {
      throw new AssertionError(e);
    }
  }
  
  public String linkFor(String htmlPage) {
    return baseUrl + "/" + htmlPage;
  }

  public String name() {
    return name;
  }

  public ApiInfo apiInfo() {
    return apiInfo;
  }
  
  public URL baseUrl() {
    return baseUrl;
  }
}