/******************************************************************************* 
 *  Copyright 2008-2012 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 * 
 *  Marketplace Web Service Products Java Library
 *  API Version: 2011-10-01
 * 
 */



package com.amazonservices.mws.products.samples;

import java.util.List;
import java.util.ArrayList;
import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 *
 * Get Competitive Pricing For SKU  Samples
 *
 *
 */
public class GetCompetitivePricingForSKUAsyncSample {

    /**
     * Just add few required parameters, and try the service
     * Get Competitive Pricing For SKU functionality
     *
     * @param args unused
     */
    public static void main(String... args) {
        
         ExecutorService executor = Executors.newFixedThreadPool(100);
         MarketplaceWebServiceProductsAsync service = new MarketplaceWebServiceProductsAsyncClient(
					ProductsConfig.accessKeyId, 
					ProductsConfig.secretAccessKey, 
					ProductsConfig.applicationName, 
					ProductsConfig.applicationVersion, 
					ProductsConfig.config,
					executor);

        /************************************************************************
         * Setup requests parameters and invoke parallel processing. Of course
         * in real world application, there will be much more than a couple of
         * requests to process.
         ***********************************************************************/
         GetCompetitivePricingForSKURequest requestOne = new GetCompetitivePricingForSKURequest();
         // @TODO: set request parameters here

         GetCompetitivePricingForSKURequest requestTwo = new GetCompetitivePricingForSKURequest();
         // @TODO: set second request parameters here

         List<GetCompetitivePricingForSKURequest> requests = new ArrayList<GetCompetitivePricingForSKURequest>();
         requests.add(requestOne);
         requests.add(requestTwo);

         invokeGetCompetitivePricingForSKU(service, requests);

         executor.shutdown();

    }


                                        
    /**
     * Get Competitive Pricing For SKU request sample
     * Gets competitive pricing and related information for a product identified by
     * the SellerId and SKU.
     *   
     * @param service instance of MarketplaceWebServiceProducts service
     * @param requests list of requests to process
     */
    public static void invokeGetCompetitivePricingForSKU(MarketplaceWebServiceProductsAsync service, List<GetCompetitivePricingForSKURequest> requests) {
        List<Future<GetCompetitivePricingForSKUResponse>> responses = new ArrayList<Future<GetCompetitivePricingForSKUResponse>>();
        for (GetCompetitivePricingForSKURequest request : requests) {
            responses.add(service.getCompetitivePricingForSKUAsync(request));
        }
        for (Future<GetCompetitivePricingForSKUResponse> future : responses) {
            while (!future.isDone()) {
                Thread.yield();
            }
            try {
                GetCompetitivePricingForSKUResponse response = future.get();
                // Original request corresponding to this response, if needed:
                GetCompetitivePricingForSKURequest originalRequest = requests.get(responses.indexOf(future));
                System.out.println("Response request id: " + response.getResponseMetadata().getRequestId());
            } catch (Exception e) {
                if (e.getCause() instanceof MarketplaceWebServiceProductsException) {
                    MarketplaceWebServiceProductsException exception = MarketplaceWebServiceProductsException.class.cast(e.getCause());
                    System.out.println("Caught Exception: " + exception.getMessage());
                    System.out.println("Response Status Code: " + exception.getStatusCode());
                    System.out.println("Error Code: " + exception.getErrorCode());
                    System.out.println("Error Type: " + exception.getErrorType());
                    System.out.println("Request ID: " + exception.getRequestId());
                    System.out.print("XML: " + exception.getXML());
                } else {
                    e.printStackTrace();
                }
            }
        }
    }
                    
}
