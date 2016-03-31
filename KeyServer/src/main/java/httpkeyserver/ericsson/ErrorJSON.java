/**
 * Copyright 2015 Javier Gusano Martínez.
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
package httpkeyserver.ericsson;

import org.slf4j.LoggerFactory;

/**
 * Class for JSON Error structure.
 * @author <a href="mailto:jgm1986@hotmail.com">Javier Gusano Martinez</a>
 */
public class ErrorJSON extends ResponseJSON {
    /**
     * Logging object.
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ErrorJSON.class);
    /**
     * Ericsson error codes definition.
     */
    public static final String ERR_REQUEST_DENIED = "request denied";
    public static final String ERR_NOT_FOUND = "spki not found";
    public static final String ERR_MALFORMED_REQUEST = "malformed request";
    public static final String ERR_UNSPECIFIED = "unspecified error";

    /**
     * Create JSON output error using defined error codes.
     * @param errorCode Error code name. Please use static variables of this
     * class. If the user specific different error code, the constructor will
     * generate an "unspecified error" JSON object.
     */
    public ErrorJSON(String errorCode) {
        super(ResponseJSON.ERROR, errorCode);
        if(!checkIfValid(errorCode)){
            logger.warn("Not valid Error code for JSON Error.");
            logger.trace("Error User label: {} | JSON: {}",errorCode ,this.toString());
            this.setOutputData(ERROR, ErrorJSON.ERR_UNSPECIFIED);
        }
    }
    
    /**
     * This method check if the current object is valid or not.
     * @param errorCode String with Erisson API error names.
     * @return True if it's valid or false if not.
     */
    private boolean checkIfValid(String errorCode){
        return errorCode.equalsIgnoreCase(ErrorJSON.ERR_MALFORMED_REQUEST) || 
                errorCode.equalsIgnoreCase(ErrorJSON.ERR_NOT_FOUND) || 
                errorCode.equalsIgnoreCase(ErrorJSON.ERR_REQUEST_DENIED) ||
                errorCode.equalsIgnoreCase(ErrorJSON.ERR_UNSPECIFIED);
    }
    
    /**
     * This method returns an JSON as String.
     * @return JSON as string.
     */
    @Override
    public String toString(){
        return super.toString();
    }
}
