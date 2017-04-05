// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `PermissionsVerifier.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

/* slice2js browser-bundle-skip */
(function(module, require, exports)
{
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */
    var __M = require("../Ice/ModuleRegistry").Ice.__M;
    var Glacier2 = require("SSLInfo").Glacier2;
    var Ice = __M.require(module, 
    [
        "../Ice/Object",
        "../Ice/ObjectPrx",
        "../Ice/Operation",
        "../Ice/Exception",
        "../Ice/Long",
        "../Ice/HashMap",
        "../Ice/HashUtil",
        "../Ice/ArrayUtil",
        "../Ice/StreamHelpers"
    ]).Ice;
    
    var Slice = Ice.Slice;
/* slice2js browser-bundle-skip-end */

    /**
     * This exception is raised if a client is denied the ability to create
     * a session with the router.
     * 
     **/
    Glacier2.PermissionDeniedException = Slice.defineUserException(
        function(reason, _cause)
        {
            Ice.UserException.call(this, _cause);
            this.reason = reason !== undefined ? reason : "";
        },
        Ice.UserException,
        "Glacier2::PermissionDeniedException",
        function(__os)
        {
            __os.writeString(this.reason);
        },
        function(__is)
        {
            this.reason = __is.readString();
        },
        true,
        false);

    /**
     * The Glacier2 permissions verifier. This is called through the
     * process of establishing a session.
     * 
     * @see Router
     * 
     **/
    Glacier2.PermissionsVerifier = Slice.defineObject(
        undefined,
        Ice.Object, undefined, 0,
        [
            "::Glacier2::PermissionsVerifier",
            "::Ice::Object"
        ],
        -1, undefined, undefined, false);

    Glacier2.PermissionsVerifierPrx = Slice.defineProxy(Ice.ObjectPrx, Glacier2.PermissionsVerifier.ice_staticId, undefined);

    Slice.defineOperations(Glacier2.PermissionsVerifier, Glacier2.PermissionsVerifierPrx,
    {
        "checkPermissions": [, 2, 1, , 2, [1], [[7], [7]], [[7]], 
        [
            Glacier2.PermissionDeniedException
        ], , ]
    });

    /**
     * The SSL Glacier2 permissions verifier. This is called through the
     * process of establishing a session.
     * 
     * @see Router
     * 
     **/
    Glacier2.SSLPermissionsVerifier = Slice.defineObject(
        undefined,
        Ice.Object, undefined, 0,
        [
            "::Glacier2::SSLPermissionsVerifier",
            "::Ice::Object"
        ],
        -1, undefined, undefined, false);

    Glacier2.SSLPermissionsVerifierPrx = Slice.defineProxy(Ice.ObjectPrx, Glacier2.SSLPermissionsVerifier.ice_staticId, undefined);

    Slice.defineOperations(Glacier2.SSLPermissionsVerifier, Glacier2.SSLPermissionsVerifierPrx,
    {
        "authorize": [, 2, 1, , 2, [1], [[Glacier2.SSLInfo]], [[7]], 
        [
            Glacier2.PermissionDeniedException
        ], , ]
    });
/* slice2js browser-bundle-skip */
    exports.Glacier2 = Glacier2;
/* slice2js browser-bundle-skip-end */
/* slice2js browser-bundle-skip */
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require : this.Ice.__require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports : this));
/* slice2js browser-bundle-skip-end */