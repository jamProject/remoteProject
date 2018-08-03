<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script>
define("modules/clean/index_rebrand", ["require", "exports", "modules/core/i18n", "modules/clean/raf_throttle", "modules/clean/auth_event_logger", "modules/clean/analytics", "modules/clean/react/index/store"], function(e, t, n, a, r, o, i) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var s = (function() {
        function e(e) {
            void 0 === e && (e = !1);
            var t = this;
            if (this.closeDrawerId = "close-panel", this.drawerClassName = "RebrandHero-drawer", this.closeDrawerClassName = "RebrandHero-drawer--closed", this.drawerSlidePixelBreakpoint = 1023, this.drawerToModalBreakpoint = 1024, this.signUpOrInLinkId = "sign-up-in", this.signInFormClassName = "signin-form", this.registerFormClassName = "register-form", this.rootElement = document.getElementsByTagName("html")[0], this.signInLinkId = "sign-in-link", this.signUpLinkId = "sign-up-link", this.triggered = !1, this.closePanel = document.getElementById(this.closeDrawerId), this.previousFocus = null, this.expSubgrowthProAXHomepage = !1, this.windowHeightBreakpoint = function(e) {
                    var t = .33 * e;
                    return Math.max(t, 330)
                }, this.maybeToggleDrawer = function() {
                    var e = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
                    if (!(e < t.drawerToModalBreakpoint)) {
                        var n = document.getElementsByTagName("iframe");
                        if (n && n.length <= 10)
                            for (var a = 0; a <= n.length; a++) {
                                var r = n[a];
                                if (r && "recaptcha challenge" === r.title && r && r.parentElement && r.parentElement.parentElement) {
                                    var o = r.parentElement.parentElement;
                                    if ("visible" === o.style.visibility) return
                                }
                            }
                        var s = void 0 !== window.pageYOffset ? window.pageYOffset : (document.documentElement || document.body).scrollTop,
                            l = !1;
                        e > t.drawerSlidePixelBreakpoint && (s <= t.windowHeightBreakpoint(e) && (l = !0), i.RebrandStore.dispatch({
                            type: l === !0 ? "showDrawer" : "hideDrawer"
                        }), t.closePanel.classList.add("animation-delay"), t.closePanel.classList.remove("button--visible"), i.RebrandStore.getState().drawer === !0 ? t.rootElement.classList.add("RebrandHero-drawer--visible") : t.rootElement.classList.remove("RebrandHero-drawer--visible"))
                    }
                }, this.hideDrawerResponsively = function(e) {
                    var n = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
                    if (n !== e) {
                        var a = void 0 !== window.pageYOffset ? window.pageYOffset : (document.documentElement || document.body).scrollTop;
                        n < t.drawerToModalBreakpoint && t.triggered === !1 ? (i.RebrandStore.dispatch({
                            type: "hideDrawer"
                        }), t.triggered = !0) : n >= t.drawerToModalBreakpoint && a <= t.windowHeightBreakpoint(n) && (i.RebrandStore.dispatch({
                            type: "showDrawer"
                        }), t.triggered = !1)
                    }
                }, this.setupListeners = function() {
                    t.closePanel && t.closePanel.addEventListener("click", function(e) {
                        e.preventDefault(), t.openDrawer(!1)
                    });
                    var e = document.getElementsByClassName(t.signInFormClassName)[0],
                        n = document.getElementsByClassName(t.registerFormClassName)[0],
                        r = document.getElementById(t.signUpOrInLinkId);
                    r && r.addEventListener("click", function(a) {
                        a.preventDefault(), e && n && t.toggleRegisterSignInForms(e, n)
                    });
                    var o = document.getElementById(t.signInLinkId);
                    o && o.addEventListener("click", function(a) {
                        a.preventDefault(), e && n && t.toggleRegisterSignInForms(e, n)
                    });
                    var i = document.getElementById(t.signUpLinkId);
                    i && i.addEventListener("click", function(a) {
                        a.preventDefault(), e && n && t.toggleRegisterSignInForms(e, n)
                    }), t.scrollThrottle = new a.RafThrottle(t.maybeToggleDrawer), window.addEventListener("scroll", function(e) {
                        t.scrollThrottle.request()
                    });
                    var s = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
                    if (t.resizeThrottle = new a.RafThrottle(function() {
                            t.hideDrawerResponsively(s)
                        }), window.addEventListener("resize", function(e) {
                            t.resizeThrottle.request()
                        }), t.expSubgrowthProAXHomepage) {
                        var l = document.getElementById(t.signUpOrInLinkId);
                        l && l.addEventListener("keydown", t.handleSignInUpFocus), setTimeout(function() {
                            var e = document.querySelector(".auth-google.button-primary.button-undefined");
                            e && e.addEventListener("keydown", t.handleLastFocus)
                        }, 0);
                        var d = document.querySelector(".login-need-help");
                        d && d.addEventListener("keydown", t.handleLastFocus)
                    }
                }, this.handleLastFocus = function(e) {
                    9 === e.keyCode && (e.shiftKey || (e.preventDefault(), t.closeDrawer(), t.moveToPreviousFocus()))
                }, this.handleSignInUpFocus = function(e) {
                    if (9 === e.keyCode && e.shiftKey) {
                        e.preventDefault();
                        var t = document.getElementsByClassName("RebrandNavigation-nav--links-item")[1];
                        null !== t && t.focus()
                    }
                }, this.focusOnNamedInput = function(e) {
                    var t = document.querySelector('input[name$="' + e + '"]');
                    t && t.focus()
                }, this.toggleRegisterSignInForms = function(e, a) {
                    var s = document.getElementById("sign-up-in"),
                        l = void 0 !== window.pageYOffset ? window.pageYOffset : (document.documentElement || document.body).scrollTop,
                        d = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
                    e && a && s && (t.isHidden(e) ? (i.RebrandStore.dispatch({
                        type: "showSignUp"
                    }), s.textContent = n._("Sign in"), o.UserActivityLogger.log("", "web_register"), r.AuthEventLogger.log_web_signup_intent(), t.focusOnNamedInput("fname"), l <= t.windowHeightBreakpoint(d) && d >= t.drawerToModalBreakpoint && t.closePanel.classList.remove("button--visible")) : (i.RebrandStore.dispatch({
                        type: "showSignIn"
                    }), s.textContent = n._("Sign up"), o.UserActivityLogger.log("", "web_login"), r.AuthEventLogger.log_web_login_intent(), t.focusOnNamedInput("login_email"), l <= t.windowHeightBreakpoint(d) && d >= t.drawerToModalBreakpoint && t.closePanel.classList.remove("button--visible")))
                }, this.openDrawer = function(e) {
                    void 0 === e && (e = !0);
                    var a = document.getElementsByClassName(t.drawerClassName)[0];
                    a && (e ? a.classList.contains(t.closeDrawerClassName) && (a.classList.remove(t.closeDrawerClassName), t.rootElement.classList.add("RebrandHero-drawer--visible"), t.closePanel.classList.add("animation-delay"), t.closePanel.classList.remove("button--visible"), t.expSubgrowthProAXHomepage && (t.previousFocus = document.activeElement instanceof HTMLElement ? document.activeElement : null, setTimeout(function() {
                        if (document.activeElement.textContent === n._("Sign up")) {
                            var e = document.querySelector('input[name$="fname"]');
                            e && e.focus()
                        } else if (document.activeElement.textContent === n._("Sign in")) {
                            var t = document.querySelector('input[name$="login_email"]');
                            t && t.focus()
                        }
                    }, 0))) : t.closeDrawer())
                }, this.closeDrawer = function() {
                    var e = document.getElementsByClassName(t.drawerClassName)[0];
                    e.classList.contains(t.closeDrawerClassName) || (e.classList.add(t.closeDrawerClassName), t.rootElement.classList.remove("RebrandHero-drawer--visible"), t.expSubgrowthProAXHomepage && (t.moveToPreviousFocus(), t.previousFocus = null))
                }, this.moveToPreviousFocus = function() {
                    if (t.previousFocus) t.previousFocus.textContent !== n._("Sign in") && t.previousFocus.textContent !== n._("Sign up") || t.previousFocus.focus();
                    else {
                        var e = document.getElementsByClassName("RebrandNavigation-nav--links-item__sign-up")[0];
                        null !== e && e.focus()
                    }
                }, this.isHidden = function(e) {
                    return !!(e.offsetWidth || e.offsetHeight || e.getClientRects().length)
                }, this.expSubgrowthProAXHomepage = e, this.expSubgrowthProAXHomepage) {
                var s = document.querySelector("body");
                s && s.classList.add("RebrandIndex--expSubgrowthProAXHomepage")
            }
            document.getElementsByClassName(this.drawerClassName)[0].classList.add("RebrandHero-drawer--init");
            var l = document.getElementsByClassName(this.signInFormClassName)[0],
                d = document.getElementsByClassName(this.registerFormClassName)[0],
                c = document.getElementById("sign-up-in");
            i.RebrandStore.subscribe(function() {
                t.openDrawer(i.RebrandStore.getState().drawer), i.RebrandStore.getState().drawer === !0 ? t.rootElement.classList.add("RebrandHero-drawer--visible") : t.rootElement.classList.remove("RebrandHero-drawer--visible"), "signIn" === i.RebrandStore.getState().form && c ? (c.textContent = n._("Sign up"), l.classList.remove("form--hidden"), d.classList.add("form--hidden"), t.closePanel.classList.remove("animation-delay"), t.closePanel.classList.add("button--visible")) : "signUp" === i.RebrandStore.getState().form && c && (c.textContent = n._("Sign in"), l.classList.add("form--hidden"), d.classList.remove("form--hidden"), t.closePanel.classList.remove("animation-delay"), t.closePanel.classList.add("button--visible"))
            }), i.RebrandStore.dispatch({
                type: "showSignUp"
            }), this.maybeToggleDrawer(), this.setupListeners()
        }
        return e
    })();
    t.RebrandIndex = s
}), define("modules/clean/react/index/components/rebrand_creation", ["require", "exports", "tslib", "external/react", "modules/clean/react/css", "modules/clean/react/index/constants", "modules/clean/react/placeholder_img", "modules/clean/react/index/components/rebrand_navigation"], function(e, t, n, a, r, o, i, s) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var l = (function(e) {
        function t(t) {
            return e.call(this, t) || this
        }
        return n.__extends(t, e), t.prototype.renderImage = function(e) {
            var t = '\n        <source srcSet="' + e.desktop.default+" 1x, " + e.desktop.hiRes + ' 2x"\n            media="all and (min-width: ' + o.breakpoints.tabletBreakPoint + ')"></source>\n        <source srcSet="' + e.tablet.default+" 1x, " + e.tablet.hiRes + ' 2x"\n            media="all and (min-width: ' + o.breakpoints.mobileBreakPoint + ")\n            and (max-width: " + o.breakpoints.tabletBreakPoint + ')"></source>\n        <source srcSet="' + e.mobile.default+" 1x, " + e.mobile.hiRes + ' 2x"\n            media="all and (max-width: ' + o.breakpoints.mobileBreakPoint + ')"></source>\n        <img src="' + e.desktop.default+'"\n            sizes="' + e.desktop.default+" 1x " + e.desktop.hiRes + ' 2x"\n            class="RebrandCreation-media--image"\n            alt="' + e.alt + '"\n        />',
                n = a.createElement("picture", {
                    className: "RebrandCreation-media--picture",
                    dangerouslySetInnerHTML: {
                        __html: t
                    }
                });
            return this.props.deferImages ? a.createElement(i.PlaceholderImg, {
                src: e.desktop.preload,
                className: "RebrandCreation-media--image",
                alt: e.alt
            }, n) : n
        }, t.prototype.renderAttribution = function(e) {
            return a.createElement("h6", {
                className: "attribution"
            }, e)
        }, t.prototype.renderWideLayout = function() {
            var e = this.props,
                t = e.content,
                r = e.experiments,
                o = t.media,
                i = "RebrandCreation";
            return a.createElement("section", {
                className: i + "__wide ob-grid-container"
            }, a.createElement(s.RebrandNavigation, n.__assign({
                theme: "burgundy-aqua"
            }, r)), a.createElement("section", {
                className: i + "-column\n                " + i + "-column-1\n                ob-grid-column\n                ob-grid-column--xlarge-8\n                ob-grid-column--large-8\n            "
            }, a.createElement("article", {
                className: i + "-text\n                    ob-grid-content\n                    ob-grid-content--pad-left-1\n                    ob-grid-content--pad-right-2\n                "
            }, a.createElement("h2", {
                className: i + "-text--title ob-title ob-title--3"
            }, t.header), a.createElement("p", {
                className: i + "-text--copy ob-copy ob-copy--standard"
            }, t.body))), a.createElement("section", {
                className: i + "-column\n                " + i + "-column-2\n                ob-grid-column\n                ob-grid-column--xlarge-8\n                ob-grid-column--large-8\n            "
            }, a.createElement("div", {
                className: "ob-grid-content"
            }, this.renderAttribution(o.left.attribution), this.renderImage(o.left))), a.createElement("section", {
                className: i + "-column\n                " + i + "-column-3\n                ob-grid-column\n                ob-grid-column--xlarge-6\n                ob-grid-column--large-6\n            "
            }, a.createElement("div", {
                className: "ob-grid-content"
            }, this.renderImage(o.right), this.renderAttribution(o.right.attribution))))
        }, t.prototype.renderCondensedLayout = function() {
            var e = this.props,
                t = e.content,
                r = e.experiments,
                o = t.media,
                i = "RebrandCreation";
            return a.createElement("section", {
                className: i + "__condensed"
            }, a.createElement("section", {
                className: i + "-column " + i + "-column-1 ob-grid-column"
            }, a.createElement(s.RebrandNavigation, n.__assign({
                theme: "burgundy"
            }, r)), a.createElement("div", {
                className: "ob-grid-content\n                    ob-grid-content--pad-left-2\n                    ob-grid-content--pad-right-2\n                    ob-grid-content--medium--pad-left-2\n                    ob-grid-content--medium--pad-right-2\n                    ob-grid-content--small--pad-left-1\n                    ob-grid-content--small--pad-right-1\n                    ob-grid-content--xsmall--pad-left-2\n                    ob-grid-content--xsmall--pad-right-2\n                "
            }, this.renderAttribution(o.left.attribution), this.renderImage(o.left))), a.createElement("section", {
                className: i + "-column " + i + "-column-2 ob-grid-column"
            }, a.createElement(s.RebrandNavigation, n.__assign({
                theme: "aqua"
            }, r)), a.createElement("div", {
                className: "ob-grid-content\n                    ob-grid-content--pad-left-4\n                    ob-grid-content--medium--pad-left-2\n                    ob-grid-content--medium--pad-right-2\n                    ob-grid-content--small--pad-left-1\n                    ob-grid-content--small--pad-right-1\n                    ob-grid-content--xsmall--pad-left-2\n                    ob-grid-content--xsmall--pad-right-2\n                "
            }, this.renderImage(o.right), this.renderAttribution(o.right.attribution))), a.createElement("section", {
                className: i + "-column\n                " + i + "-column-3\n                ob-grid-column\n                ob-grid-column--xlarge-7\n                ob-grid-column--large-7\n                ob-grid-column--medium-24\n                ob-grid-column--small-24\n            "
            }, a.createElement(s.RebrandNavigation, n.__assign({
                theme: "aqua"
            }, r)), a.createElement("article", {
                className: i + "-text ob-grid-content\n                    ob-grid-content--medium--pad-left-2\n                    ob-grid-content--medium--pad-right-9\n                    ob-grid-content--small--pad-1\n                    ob-grid-content--xsmall--pad-2\n                "
            }, a.createElement("h2", {
                className: i + "-text--title ob-title ob-title--3"
            }, t.header), a.createElement("p", {
                className: i + "-text--copy ob-copy ob-copy--standard"
            }, t.body))))
        }, t.prototype.render = function() {
            return a.createElement("section", {
                className: "RebrandCreation ob-grid"
            }, this.renderWideLayout(), this.renderCondensedLayout())
        }, t
    })(a.Component);
    t.RebrandCreation = r(l, ["/static/css/index/components/rebrand_creation-vflfjBRHX.css"])
}), define("modules/clean/react/index/components/rebrand_hero", ["require", "exports", "tslib", "external/react", "modules/clean/react/css", "modules/clean/react/index/components/rebrand_navigation", "modules/clean/react/index/store"], function(e, t, n, a, r, o, i) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var s = (function(e) {
        function t(t) {
            var n = e.call(this, t) || this;
            return n.handleDrawer = function() {
                i.RebrandStore.dispatch({
                    type: "showDrawer"
                }), i.RebrandStore.dispatch({
                    type: "showSignUp"
                })
            }, n.handleScroll = function() {
                var e = function(e, t, n, a) {
                        return (e /= a / 2) < 1 ? n / 2 * e * e * e * e + t : -n / 2 * ((e -= 2) * e * e * e - 2) + t
                    },
                    t = document.getElementsByClassName("RebrandCreation")[0],
                    n = document.documentElement.scrollTop,
                    a = t.getBoundingClientRect().top + document.documentElement.scrollTop,
                    r = (new Date).getTime(),
                    o = setInterval(function() {
                        var t = (new Date).getTime() - r,
                            i = e(t, n, a - n, 500);
                        window.scrollTo(0, i), t >= 500 && clearInterval(o)
                    }, 1e3 / 60)
            }, n
        }
        return n.__extends(t, e), t.prototype.renderHeader = function() {
            var e = this.props.content;
            return a.createElement("header", {
                className: "RebrandHero-header"
            }, a.createElement("h1", {
                className: "RebrandHero--title ob-title ob-title--1"
            }, e.header), a.createElement("p", {
                className: "RebrandHero--copy ob-copy ob-copy--standard"
            }, e.body), a.createElement("button", {
                className: "RebrandHero--cta ob-button ob-button__cta ob-button--white",
                onClick: this.handleDrawer
            }, e.cta), a.createElement("button", {
                className: "RebrandHero--waypoint ob-button ob-button--waypoint",
                "aria-label": "waypoint",
                onClick: this.handleScroll
            }))
        }, t.prototype.render = function() {
            var e = this.props.experiments.expSubgrowthProAXHomepage ? "visible" : void 0;
            return a.createElement("section", {
                className: "RebrandHero ob-grid"
            }, a.createElement(o.RebrandNavigation, {
                theme: "burgundy-aqua",
                ctaFocus: "signIn",
                ctaStyle: "outline",
                axVisibility: e
            }), a.createElement("section", {
                className: "RebrandHero-container ob-grid-container"
            }, a.createElement("aside", {
                className: "RebrandHero-aside\n              ob-grid-column\n              ob-grid-column--xlarge-16\n              ob-grid-column--large-16\n              ob-grid-column--medium-20\n              ob-grid-column--small-24\n              ob-grid-column--xsmall-24\n            "
            }, a.createElement("div", {
                className: "ob-grid-content\n                ob-grid-content--pad-2\n                ob-grid-content--xlarge--pad-2\n                ob-grid-content--large--pad-2\n                ob-grid-content--medium--pad-2\n                ob-grid-content--small--pad-1\n                ob-grid-content--xsmall--pad-2\n              "
            }, this.renderHeader()))))
        }, t
    })(a.Component);
    t.RebrandHero = r(s, ["/static/css/index/components/rebrand_hero-vflkvw3uD.css"])
}), define("modules/clean/react/index/components/rebrand_logo", ["require", "exports", "tslib", "external/react", "modules/clean/react/css", "modules/clean/static_urls"], function(e, t, n, a, r, o) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.DropboxLogoGlyphs = {
        default: "images/index/rebrand/logos/glyphs/glyph_blue.svg",
        aqua: "images/index/rebrand/logos/glyphs/glyph_cherry.svg",
        blackened: "images/index/rebrand/logos/glyphs/glyph_blue.svg",
        burgundy: "images/index/rebrand/logos/glyphs/glyph_aqua.svg",
        "burgundy-aqua": "images/index/rebrand/logos/glyphs/glyph_aqua.svg",
        peach: "images/index/rebrand/logos/glyphs/glyph_dark_blue.svg",
        watermelon: "images/index/rebrand/logos/glyphs/glyph_yellow.svg",
        white: "images/index/rebrand/logos/glyphs/glyph_blue.svg"
    };
    var i = (s = (function(e) {
        function r(t) {
            return e.call(this, t) || this
        }
        return n.__extends(r, e), r.prototype.renderLogoGlyph = function(e) {
            return a.createElement("img", {
                src: o.static_url("/static/" + t.DropboxLogoGlyphs[e || this.props.theme || "default"]),
                className: "DropboxLogo--glyph",
                alt: "Dropbox"
            })
        }, r.prototype.renderLogoWordmark = function(e) {
            var t = {
                default: "images/index/rebrand/logos/wordmarks/wordmark_black.svg",
                aqua: "images/index/rebrand/logos/wordmarks/wordmark_black.svg",
                blackened: "images/index/rebrand/logos/wordmarks/wordmark_white.svg",
                burgundy: "images/index/rebrand/logos/wordmarks/wordmark_white.svg",
                "burgundy-aqua": "images/index/rebrand/logos/wordmarks/wordmark_white.svg",
                peach: "images/index/rebrand/logos/wordmarks/wordmark_black.svg",
                watermelon: "images/index/rebrand/logos/wordmarks/wordmark_white.svg",
                white: "images/index/rebrand/logos/wordmarks/wordmark_black.svg"
            };
            return a.createElement("img", {
                src: o.static_url("/static/" + t[e || this.props.theme || "default"]),
                className: "DropboxLogo--wordmark",
                alt: ""
            })
        }, r.prototype.render = function() {
            return a.createElement("div", {
                className: "DropboxLogo"
            }, this.renderLogoGlyph(), this.renderLogoWordmark())
        }, r
    })(a.Component), s.defaultProps = {
        theme: "default"
    }, s);
    t.DropboxLogo = r(i, ["/static/css/index/obsidian/logo-vflM1D8V1.css"]);
    var s
}), define("modules/clean/react/index/components/rebrand_media", ["require", "exports", "tslib", "external/classnames", "external/react", "modules/clean/react/css", "modules/clean/static_urls", "modules/clean/react/index/constants", "modules/clean/react/placeholder_img", "modules/clean/react/index/components/rebrand_navigation", "modules/clean/react/rebrand/elements/inline_video"], function(e, t, n, a, r, o, i, s, l, d, c) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var m = (function(e) {
        function t(t) {
            return e.call(this, t) || this
        }
        return n.__extends(t, e), t.prototype.renderCTA = function() {
            var e = this.props.content;
            return r.createElement("a", {
                href: e.cta.link,
                className: "ob-button ob-button--white"
            }, e.cta.button)
        }, t.prototype.renderImage = function() {
            var e = this.props.content;
            if (e.images) {
                var t = '\n          <source srcSet="' + i.static_url("/static/" + e.images.desktop.default) + " 1x,\n            " + i.static_url("/static/" + e.images.desktop.hiRes) + ' 2x"\n          media="(' + s.breakpoints.tabletBreakPoint + ')"></source>\n          <source srcSet="' + i.static_url("/static/" + e.images.tablet.default) + " 1x,\n            " + i.static_url("/static/" + e.images.tablet.hiRes + "}") + ' 2x"\n            media="(min-width: ' + s.breakpoints.mobileBreakPoint + ") and (max-width:               " + s.breakpoints.tabletBreakPoint + ')"></source>\n          <source srcSet="' + i.static_url("/static/" + e.images.mobile.default) + " 1x,\n            " + i.static_url("/static/" + e.images.mobile.hiRes) + ' 2x"\n            media="(max-width: ' + s.breakpoints.mobileBreakPoint + ')"></source>\n          <img sizes="' + i.static_url("/static/" + e.images.desktop.default) + " 1x,\n            " + i.static_url("/static/" + e.images.desktop.hiRes) + ' 2x"\n            src="' + i.static_url("/static/" + e.images.desktop.default) + '"\n            class="RebrandMedia-media--image" alt="' + e.images.alt + '" />',
                    n = r.createElement("picture", {
                        className: "RebrandMedia-media--picture",
                        dangerouslySetInnerHTML: {
                            __html: t
                        }
                    });
                return this.props.deferImages ? r.createElement(l.PlaceholderImg, {
                    src: e.images.desktop.preload,
                    className: "RebrandMedia-media--image",
                    alt: e.images.alt
                }, n) : n
            }
        }, t.prototype.renderVideo = function() {
            var e = this.props,
                t = e.content,
                n = e.locale;
            if (t.videos) {
                var a = t.videos.poster.hiRes,
                    o = {
                        mp4: {
                            default: t.videos.desktop.mp4.default,
                            hiRes: t.videos.desktop.mp4.hiRes
                        },
                        webm: {
                            default: t.videos.desktop.webm.default,
                            hiRes: t.videos.desktop.webm.hiRes
                        }
                    },
                    i = '\n        <source srcSet="\n          ' + t.videos.static.mobile.default+" 1x,\n          " + t.videos.static.mobile.hiRes + ' 2x"\n          media="all and (max-width: ' + s.breakpoints.mobileBreakPoint + ')">\n        </source>\n        <img src="' + t.videos.static.mobile.default+'"\n          sizes="' + t.videos.static.mobile.default+" 1x,\n          " + t.videos.static.mobile.hiRes + ' 2x"\n          className="RebrandMedia-media--image"\n        />\n      ',
                    d = r.createElement("section", {
                        className: "RebrandMedia-media-container"
                    }, r.createElement("div", {
                        className: "video-wrapper"
                    }, r.createElement(c.InlineVideo, {
                        locale: n,
                        desktopSrc: o.mp4.default,
                        desktopHiResSrc: o.mp4.hiRes,
                        staticContentHost: "rebrand.dropboxstatic.com",
                        poster: a,
                        description: t.videos.description
                    })), r.createElement("picture", {
                        className: "RebrandMedia-media--picture",
                        dangerouslySetInnerHTML: {
                            __html: i
                        }
                    }));
                return this.props.deferImages ? r.createElement(l.PlaceholderImg, {
                    src: t.videos.poster.preload,
                    className: "RebrandMedia-media--image"
                }, d) : d
            }
        }, t.prototype.render = function() {
            var e = this.props,
                t = e.content,
                n = e.theme,
                o = "RebrandMedia",
                i = a((l = {}, l[o] = !0, l["ob-grid"] = !0, l[o + "__theme-create"] = "create" === n, l[o + "__theme-collaborate"] = "collaborate" === n, l)),
                s = "default";
            return "create" === n ? s = "watermelon" : "collaborate" === n && (s = "peach"), r.createElement("section", {
                className: i
            }, r.createElement(d.RebrandNavigation, {
                theme: s
            }), r.createElement("article", {
                className: a((c = {}, c[o + "-container"] = !0, c.reversedLayout = this.props.reversedLayout === !0, c["ob-grid-container"] = !0, c))
            }, r.createElement("div", {
                className: o + "-text\n              ob-grid-column\n              ob-grid-column--xlarge-7\n              ob-grid-column--large-7\n              ob-grid-column--medium-14\n              ob-grid-column--small-24\n              ob-grid-column--xmedium-24\n            "
            }, r.createElement("div", {
                className: a({
                    "ob-grid-content": !0,
                    "ob-grid-content--pad-left-1": this.props.reversedLayout !== !0,
                    "ob-grid-content--pad-right-1": this.props.reversedLayout === !0,
                    "ob-grid-content--medium--pad-2": !0,
                    "ob-grid-content--small--pad-1": !0,
                    "ob-grid-content--xsmall--pad-2": !0
                })
            }, r.createElement("h2", {
                className: o + "-text--headline ob-title ob-title--3"
            }, t.header), r.createElement("p", {
                className: o + "-text--body ob-copy ob-copy--standard"
            }, t.body), t.cta && this.renderCTA())), r.createElement("div", {
                className: o + "-media\n              ob-grid-column\n              ob-grid-column--xlarge-17\n              ob-grid-column--large-17\n              ob-grid-column--medium-24\n              ob-grid-column--small-24\n              ob-grid-column--xmedium-24\n            "
            }, r.createElement("div", {
                className: "ob-grid-content\n                ob-grid-content--pad-1\n                ob-grid-content--medium--pad-2\n                ob-grid-content--small--pad-1\n                ob-grid-content--xsmall--pad-2\n              "
            }, t.videos && this.renderVideo(), t.images && this.renderImage()))));
            var l, c
        }, t
    })(r.Component);
    t.RebrandMedia = o(m, ["/static/css/index/components/rebrand_media-vfl3eL3IK.css"])
}), define("modules/clean/react/index/components/rebrand_mobile_navigation", ["require", "exports", "tslib", "external/react", "modules/clean/react/css", "modules/core/i18n", "modules/clean/stormcrow/experiment", "modules/clean/react/index/store", "modules/clean/react/index/components/rebrand_logo"], function(e, t, n, a, r, o, i, s, l) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var d = (function(e) {
        function t(t) {
            var n = e.call(this, t) || this;
            return n.handleDrawer = function() {
                s.RebrandStore.dispatch({
                    type: "hideMobileNav"
                }), s.RebrandStore.dispatch({
                    type: "showDrawer"
                }), s.RebrandStore.dispatch({
                    type: "showSignIn"
                })
            }, n.handleMobileNav = function() {
                s.RebrandStore.dispatch({
                    type: "hideMobileNav"
                })
            }, n.state = {
                experiments: s.RebrandStore.getState().experiments
            }, n
        }
        return n.__extends(t, e), Object.defineProperty(t.prototype, "expSubgrowthBizSwapHpLinks", {
            get: function() {
                return i.Experiment(this.state.experiments.expSubgrowthBizHpSwapLinks)
            },
            enumerable: !0,
            configurable: !0
        }), t.prototype.renderIndividualLink = function(e) {
            return a.createElement("a", {
                href: "/individual",
                className: e + "-nav--link"
            }, o._("For individuals"))
        }, t.prototype.render = function() {
            var e = "RebrandMobileNavigation";
            return this.expSubgrowthBizSwapHpLinks.variantIs("V1") ? a.createElement("section", {
                className: "" + e
            }, a.createElement("header", {
                className: e + "-header"
            }, a.createElement(l.DropboxLogo, {
                theme: "default"
            }), a.createElement("button", {
                className: "ob-button ob-button--close",
                onClick: this.handleMobileNav,
                "aria-label": o._("Close")
            }, o._("Close"))), a.createElement("nav", {
                className: e + "-nav"
            }, a.createElement("button", {
                className: e + "-nav--link ob-button ob-button--link",
                onClick: this.handleDrawer
            }, o._("Sign in")), a.createElement("a", {
                href: "/install",
                className: e + "-nav--link"
            }, o._("Download")), this.renderIndividualLink(e), a.createElement("a", {
                href: "/business",
                className: e + "-nav--link"
            }, o._("For teams")))) : a.createElement("section", {
                className: "" + e
            }, a.createElement("header", {
                className: e + "-header"
            }, a.createElement(l.DropboxLogo, {
                theme: "default"
            }), a.createElement("button", {
                className: "ob-button ob-button--close",
                onClick: this.handleMobileNav,
                "aria-label": o._("Close")
            }, o._("Close"))), a.createElement("nav", {
                className: e + "-nav"
            }, a.createElement("button", {
                className: e + "-nav--link ob-button ob-button--link",
                onClick: this.handleDrawer
            }, o._("Sign in")), a.createElement("a", {
                href: "/install",
                className: e + "-nav--link"
            }, o._("Download")), a.createElement("a", {
                href: "/business",
                className: e + "-nav--link"
            }, o._("For teams")), this.renderIndividualLink(e)))
        }, t
    })(a.Component);
    t.RebrandMobileNavigation = r(d, ["/static/css/index/components/rebrand_condensed_navigation-vflfLTlP1.css"])
}), define("modules/clean/react/index/components/rebrand_navigation", ["require", "exports", "tslib", "external/classnames", "external/react", "modules/clean/react/css", "modules/core/i18n", "modules/clean/stormcrow/experiment", "modules/clean/react/index/store", "modules/clean/react/index/components/rebrand_logo"], function(e, t, n, a, r, o, i, s, l, d) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var c = (function(e) {
        function t(t) {
            var n = e.call(this, t) || this;
            return n.handleMobileNav = function(e) {
                e.stopPropagation(), l.RebrandStore.dispatch({
                    type: "showMobileNav"
                }), l.RebrandStore.dispatch({
                    type: "hideDrawer"
                })
            }, n.handleDrawer = function(e, t) {
                e.stopPropagation(), l.RebrandStore.dispatch({
                    type: "show" + t
                }), l.RebrandStore.dispatch({
                    type: "showDrawer"
                })
            }, n.handleDrawerButtonFocus = function(e) {
                if ("ON" === n.expSubgrowthProAXHomepage) {
                    e.stopPropagation();
                    var t = document.getElementsByTagName("html")[0],
                        a = document.getElementById("sign-up-in");
                    a && t.classList.contains("RebrandHero-drawer--visible") && a.focus()
                }
            }, n.state = {
                experiments: l.RebrandStore.getState().experiments,
                gates: l.RebrandStore.getState().gates
            }, n
        }
        return n.__extends(t, e), Object.defineProperty(t.prototype, "expSubgrowthProAXHomepage", {
            get: function() {
                return this.state.experiments.expSubgrowthProAXHomepage
            },
            enumerable: !0,
            configurable: !0
        }), Object.defineProperty(t.prototype, "expSubgrowthBizSwapHpLinks", {
            get: function() {
                return s.Experiment(this.state.experiments.expSubgrowthBizHpSwapLinks)
            },
            enumerable: !0,
            configurable: !0
        }), t.prototype.renderCTA = function() {
            var e = 0,
                t = !1;
            "ON" === this.expSubgrowthProAXHomepage && (this.props.axVisibility && "visible" === this.props.axVisibility ? (e = 0, t = !1) : (e = -1, t = !0));
            var n = a((o = {
                "ob-button": !0,
                "ob-button--link": !0
            }, o["RebrandNavigation-nav--links-item"] = !0, o));
            return this.expSubgrowthBizSwapHpLinks.variantIs("V1") ? r.createElement("div", {
                className: "RebrandNavigation-nav-cta",
                "aria-hidden": t
            }, r.createElement("a", {
                className: n,
                href: "/individual",
                tabIndex: e
            }, i._("For Individuals")), r.createElement("a", {
                className: n,
                href: "/business",
                tabIndex: e
            }, i._("For Teams"))) : r.createElement("div", {
                className: "RebrandNavigation-nav-cta",
                "aria-hidden": t
            }, r.createElement("a", {
                className: n,
                href: "/business",
                tabIndex: e
            }, i._("For Teams")), r.createElement("a", {
                className: n,
                href: "/individual",
                tabIndex: e
            }, i._("For Individuals")));
            var o
        }, t.prototype.render = function() {
            var e = this,
                t = "RebrandNavigation",
                n = this.props.axVisibility && "visible" === this.props.axVisibility ? {
                    tabIndexValue: 0,
                    ariaHiddenValue: !1
                } : {
                    tabIndexValue: -1,
                    ariaHiddenValue: !0
                },
                o = n.tabIndexValue,
                s = n.ariaHiddenValue;
            return r.createElement("section", {
                className: t + " " + t + "-theme__" + this.props.theme,
                "aria-hidden": s
            }, r.createElement("div", {
                className: t + "-container"
            }, r.createElement("div", {
                className: t + "-container-content"
            }, r.createElement(d.DropboxLogo, {
                theme: this.props.theme
            }), r.createElement("nav", {
                className: t + "-nav"
            }, !this.props.hideCTA && this.renderCTA(), r.createElement("section", {
                className: t + "-nav--links"
            }, r.createElement("button", {
                className: a((l = {}, l[t + "-nav--links-item"] = !0, l[t + "-nav--links-item__sign-up"] = !0, l[t + "-nav--links-item__hidden"] = "signUp" === this.props.ctaFocus, l["ob-button"] = !0, l["ob-button--link"] = !0, l)),
                onClick: function(t) {
                    e.handleDrawer(t, "SignIn")
                },
                tabIndex: o,
                onFocus: this.handleDrawerButtonFocus
            }, i._("Sign in")), r.createElement("button", {
                className: a((c = {}, c[t + "-nav--links-item"] = !0, c[t + "-nav--links-item__sign-up"] = !0, c[t + "-nav--links-item__hidden"] = "signIn" === this.props.ctaFocus, c["ob-button"] = !0, c["ob-button--link"] = !0, c)),
                onClick: function(t) {
                    e.handleDrawer(t, "SignUp")
                },
                onFocus: this.handleDrawerButtonFocus,
                tabIndex: o
            }, i._("Sign up")), r.createElement("button", {
                className: a((m = {}, m[t + "-nav--links-item"] = !0, m["ob-button"] = !0, m["ob-button--cheeseburger"] = !0, m["ob-button--cheeseburger__white"] = "black" === this.props.theme || "burgundy" === this.props.theme || "burgundy-aqua" === this.props.theme || "watermelon" === this.props.theme, m)),
                onClick: this.handleMobileNav,
                tabIndex: o
            }, i._("Menu")))))));
            var l, c, m
        }, t.defaultProps = {
            ctaFocus: "signUp",
            theme: "default"
        }, t
    })(r.Component);
    t.RebrandNavigation = o(c, ["/static/css/index/components/rebrand_navigation-vflPUqBav.css"])
}), define("modules/clean/react/index/components/rebrand_sidekick", ["require", "exports", "tslib", "external/react", "modules/clean/react/css", "modules/clean/react/index/components/rebrand_navigation", "modules/clean/react_format"], function(e, t, n, a, r, o, i) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var s = "RebrandSidekick",
        l = (function(e) {
            function t(t) {
                return e.call(this, t) || this
            }
            return n.__extends(t, e), t.prototype.renderSecondaryCTA = function(e) {
                return a.createElement("p", {
                    className: s + "--item-secondary ob-copy ob-copy--mini"
                }, i.reactFormat(e.text, {
                    a: a.createElement("a", {
                        href: e.link,
                        className: s + "--item-secondary-link"
                    })
                }))
            }, t.prototype.render = function() {
                var e = this.props.content,
                    t = e.button,
                    n = e.link,
                    r = e.title,
                    o = e.secondary;
                return a.createElement("div", {
                    className: s + "--item\n        ob-grid-column\n        ob-grid-column--xlarge-12\n        ob-grid-column--large-12\n        ob-grid-column--medium-14\n        ob-grid-column--small-24\n        ob-grid-column--xsmall-24\n      "
                }, a.createElement("div", {
                    className: "\n        ob-grid-content\n        ob-grid-content--pad-2\n        ob-grid-content--xlarge--pad-2\n        ob-grid-content--large--pad-2\n        ob-grid-content--medium--pad-2\n        ob-grid-content--small--pad-1\n        ob-grid-content--xsmall--pad-2\n      "
                }, a.createElement("h2", {
                    className: "\n          " + s + "--item-title\n          ob-title\n          ob-title--2\n        "
                }, r), a.createElement("a", {
                    href: n,
                    className: "\n          " + s + "--item-button\n          ob-button\n          ob-button--blue\n        "
                }, t), o && this.renderSecondaryCTA(o)))
            }, t
        })(a.Component),
        d = (function(e) {
            function t(t) {
                return e.call(this, t) || this
            }
            return n.__extends(t, e), t.prototype.render = function() {
                var e = this.props,
                    t = e.content,
                    n = e.hideCTA;
                return a.createElement("section", {
                    className: "RebrandSidekick ob-grid"
                }, a.createElement(o.RebrandNavigation, {
                    theme: "default",
                    hideCTA: n
                }), a.createElement("section", {
                    className: "RebrandSidekick-container ob-grid-container"
                }, a.createElement(l, {
                    content: t.individual
                }), a.createElement(l, {
                    content: t.teams
                })))
            }, t.defaultProps = {
                hideCTA: !0
            }, t
        })(a.Component);
    t.RebrandSidekick = r(d, ["/static/css/index/components/rebrand_sidekick-vflqUQa-1.css"])
}), define("modules/clean/react/index/constants", ["require", "exports"], function(e, t) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    }), t.breakpoints = {
        largeDesktopBreakPoint: "1440px",
        desktopBreakPoint: "1440px",
        tabletBreakPoint: "1024px",
        mobileBreakPoint: "767px",
        smallMobileBreakPoint: "375px"
    }, t.breakpointsName = {
        xlarge: t.breakpoints.largeDesktopBreakPoint,
        large: t.breakpoints.desktopBreakPoint,
        medium: t.breakpoints.tabletBreakPoint,
        small: t.breakpoints.mobileBreakPoint,
        xsmall: t.breakpoints.smallMobileBreakPoint
    }
}), define("modules/clean/react/index/rebrand_page", ["require", "exports", "tslib", "external/classnames", "external/react", "modules/clean/react/css", "modules/clean/web_timing_logger", "modules/clean/react/async/loadable", "modules/clean/react/index/components/rebrand_hero", "modules/clean/react/index/components/rebrand_creation", "modules/clean/react/index/components/rebrand_media", "modules/clean/react/index/components/rebrand_sidekick", "modules/clean/react/index/components/rebrand_mobile_navigation", "modules/clean/react/index/store"], function(e, t, n, a, r, o, i, s, l, d, c, m, u, b) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var p = s.Loadable({
            loader: function() {
                return i.waitForTTI().then(function() {
                    return new Promise(function(t, n) {
                        e(["modules/clean/react/index/components/rebrand_footer_plane"], t, n)
                    }).then(function(e) {
                        return e.RebrandFooterPlane
                    })
                })
            }
        }),
        g = (function(e) {
            function t(t) {
                var n = e.call(this, t) || this;
                return n.handleEvents = function(e) {
                    e.stopPropagation();
                    var t = void 0 !== window.pageYOffset ? window.pageYOffset : (document.documentElement || document.body).scrollTop,
                        n = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
                    window.innerWidth >= 1023 && t >= .33 * n && b.RebrandStore.dispatch({
                        type: "hideDrawer"
                    })
                }, n.state = {
                    showMobileNav: !1
                }, window.innerWidth < 1024 && b.RebrandStore.dispatch({
                    type: "hideDrawer"
                }), n
            }
            return n.__extends(t, e), t.prototype.componentWillMount = function() {
                var e = this;
                b.RebrandStore.dispatch({
                    type: "setExperiments",
                    experiments: this.props.experiments
                }), b.RebrandStore.dispatch({
                    type: "setGates",
                    gates: this.props.gates
                }), b.RebrandStore.subscribe(function() {
                    return e.setState({
                        showMobileNav: b.RebrandStore.getState().mobileNav
                    })
                })
            }, t.prototype.render = function() {
                var e = this.props,
                    t = e.content,
                    n = e.experiments,
                    o = e.gates,
                    i = e.locale,
                    s = e.isEdge,
                    b = e.isInternetExplorer,
                    g = e.footerContent;
                return r.createElement("main", {
                    className: a({
                        IndexRebrandPage: !0,
                        "AtlasGrotesk-Regular-Web": !0,
                        "ob-type": !0,
                        "IndexRebrandPage-mobile__visible": this.state.showMobileNav,
                        "IndexRebrandPage-browser__internet-explorer": b,
                        "IndexRebrandPage-browser__edge": s
                    }),
                    onClick: this.handleEvents
                }, this.state.showMobileNav && r.createElement(u.RebrandMobileNavigation, null), r.createElement(l.RebrandHero, {
                    content: t.hero,
                    experiments: n
                }), r.createElement(d.RebrandCreation, {
                    content: t.sections.keep,
                    deferImages: o.deferImageLoading,
                    experiments: n
                }), r.createElement(c.RebrandMedia, {
                    content: t.sections.collaborate,
                    reversedLayout: !0,
                    locale: i,
                    theme: "collaborate",
                    deferImages: o.deferImageLoading
                }), r.createElement(c.RebrandMedia, {
                    content: t.sections.create,
                    locale: i,
                    theme: "create",
                    deferImages: o.deferImageLoading
                }), r.createElement(m.RebrandSidekick, {
                    content: t.sidekick,
                    hideCTA: !s && !b
                }), r.createElement(p, {
                    theme: "blackened",
                    footerContent: g,
                    homePreloadJsInHomepage: o.homePreloadJsInHomepage
                }))
            }, t
        })(r.Component);
    t.IndexRebrandPage = o(g, ["/static/css/index/rebrand_page-vflw_Ejt-.css"])
}), define("modules/clean/react/index/store", ["require", "exports", "redux", "modules/clean/redux/devtools", "external/redux-thunk"], function(e, t, n, a, r) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var o = {},
        i = {},
        s = function(e, t) {
            switch (void 0 === e && (e = o), t.type) {
                case "setExperiments":
                    return t.experiments;
                default:
                    return e
            }
        },
        l = function(e, t) {
            switch (void 0 === e && (e = i), t.type) {
                case "setGates":
                    return t.gates;
                default:
                    return e
            }
        },
        d = function(e, t) {
            switch (void 0 === e && (e = !1), t.type) {
                case "showMobileNav":
                    return !0;
                case "hideMobileNav":
                    return !1;
                default:
                    return e
            }
        },
        c = function(e, t) {
            switch (void 0 === e && (e = !0), t.type) {
                case "showDrawer":
                    return !0;
                case "hideDrawer":
                    return !1;
                default:
                    return e
            }
        },
        m = function(e, t) {
            switch (void 0 === e && (e = "signUp"), t.type) {
                case "showSignUp":
                    return "signUp";
                case "showSignIn":
                    return "signIn";
                default:
                    return e
            }
        },
        u = n.combineReducers({
            drawer: c,
            experiments: s,
            form: m,
            gates: l,
            mobileNav: d
        });
    t.RebrandStore = n.createStore(u, {}, a.composeEnhancersWithDevTools(n.applyMiddleware(r)))
}), define("modules/clean/react/placeholder_img", ["require", "exports", "tslib", "external/react", "modules/clean/web_timing_logger"], function(e, t, n, a, r) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var o = (function(e) {
        function t(t) {
            var n = e.call(this, t) || this;
            return n.state = {
                isPostTTI: !1
            }, n
        }
        return n.__extends(t, e), t.prototype.componentDidMount = function() {
            var e = this;
            r.waitForTTI().then(function() {
                e.setState({
                    isPostTTI: !0
                })
            })
        }, t.prototype.render = function() {
            var e = this.props,
                t = e.src,
                n = e.className,
                r = e.alt,
                o = e.children;
            return this.state.isPostTTI ? a.createElement("span", null, o) : a.createElement("img", {
                src: t,
                sizes: t + " 1x " + t + " 2x",
                className: n,
                alt: r
            })
        }, t
    })(a.Component);
    t.PlaceholderImg = o
});
//# sourceMappingURL=pkg-index-rebrand.min.js-vfldyqT1l.map

</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>