/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.crepecake.builder;

import com.google.cloud.tools.crepecake.http.Authorization;
import com.google.cloud.tools.crepecake.registry.RegistryAuthenticationFailedException;
import com.google.cloud.tools.crepecake.registry.RegistryAuthenticators;
import com.google.cloud.tools.crepecake.registry.RegistryException;
import java.io.IOException;
import java.util.concurrent.Callable;

class AuthenticatePullStep implements Callable<Authorization> {

  private final BuildConfiguration buildConfiguration;

  AuthenticatePullStep(BuildConfiguration buildConfiguration) {
    this.buildConfiguration = buildConfiguration;
  }

  @Override
  public Authorization call()
      throws RegistryAuthenticationFailedException, IOException, RegistryException {
    return RegistryAuthenticators.forOther(
            buildConfiguration.getBaseImageServerUrl(), buildConfiguration.getBaseImageName())
        .authenticate();
  }
}
