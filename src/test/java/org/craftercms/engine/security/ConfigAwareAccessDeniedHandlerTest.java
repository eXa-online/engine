/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.craftercms.engine.security;

import org.craftercms.commons.http.RequestContext;
import org.craftercms.engine.test.utils.ConfigAwareTestBase;
import org.craftercms.security.exception.AccessDeniedException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.craftercms.engine.security.ConfigAwareAccessDeniedHandler.ACCESS_DENIED_ERROR_PAGE_URL_KEY;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link org.craftercms.engine.security.ConfigAwareAccessDeniedHandler}.
 *
 * @author avasquez
 */
public class ConfigAwareAccessDeniedHandlerTest extends ConfigAwareTestBase {

    private ConfigAwareAccessDeniedHandler handler;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        handler = new ConfigAwareAccessDeniedHandler();
        handler.setErrorPageUrl("/access-denied");
    }

    @Test
    public void testProcessRequest() throws Exception {
        handler.handle(RequestContext.getCurrent(), new AccessDeniedException(""));

        assertEquals(config.getString(ACCESS_DENIED_ERROR_PAGE_URL_KEY),
                     ((MockHttpServletResponse)RequestContext.getCurrent().getResponse()).getForwardedUrl());
    }
    
}
