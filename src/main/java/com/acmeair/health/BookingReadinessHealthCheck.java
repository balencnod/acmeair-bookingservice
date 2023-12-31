/*******************************************************************************
 * Copyright (c) 2018 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.acmeair.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.Startup;

import com.acmeair.service.BookingService;

@Readiness
@Startup
@ApplicationScoped
public class BookingReadinessHealthCheck implements HealthCheck {
  
  @Inject
  BookingService bookingService;

  public HealthCheckResponse call() {
   
    HealthCheckResponseBuilder builder = HealthCheckResponse.named("BookingServiceReadinessCheck");
    
    if (bookingService.isConnected()) {
      builder = builder.up();
    } else {
      builder = builder.down();
    }

    return builder.build();
  }
}