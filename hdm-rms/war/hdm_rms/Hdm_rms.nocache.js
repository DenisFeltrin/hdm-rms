function Hdm_rms(){var T='',Bb='" for "gwt:onLoadErrorFn"',zb='" for "gwt:onPropertyErrorFn"',mb='"><\/script>',bb='#',Zb='.cache.html',db='/',pb='//',Tb='2D71FA0094AFA323EE97D8940F61E5FA',Ub='7DC7AF3DA5822A0DB51F7460A1E4E5E0',Yb=':',tb='::',fc='<script defer="defer">Hdm_rms.onInjectionDone(\'Hdm_rms\')<\/script>',lb='<script id="',wb='=',cb='?',Vb='A610A2E78594A1EB7364BAE7BD24EECA',yb='Bad handler "',ec='DOMContentLoaded',Wb='E27A4D038DAE48F97419AC6018A08A24',Xb='E8E8633C5BD903B34C4C17837D347BD4',U='Hdm_rms',ib='Hdm_rms.nocache.js',sb='Hdm_rms::',nb='SCRIPT',kb='__gwt_marker_Hdm_rms',ob='base',gb='baseUrl',X='begin',W='bootstrap',fb='clear.cache.gif',vb='content',ab='end',Nb='gecko',Ob='gecko1_8',Y='gwt.codesvr=',Z='gwt.hosted=',$='gwt.hybrid',$b='gwt/clean/clean.css',Ab='gwt:onLoadErrorFn',xb='gwt:onPropertyErrorFn',ub='gwt:property',dc='head',Rb='hosted.html?Hdm_rms',cc='href',Kb='ie10',Mb='ie8',Lb='ie9',Cb='iframe',eb='img',Db="javascript:''",_b='link',Qb='loadExternalRefs',qb='meta',Fb='moduleRequested',_='moduleStartup',Jb='msie',rb='name',Eb='position:absolute;width:0;height:0;border:none',ac='rel',Ib='safari',hb='script',Sb='selectingPermutation',V='startup',bc='stylesheet',jb='undefined',Pb='unknown',Gb='user.agent',Hb='webkit';var m=window,n=document,o=m.__gwtStatsEvent?function(a){return m.__gwtStatsEvent(a)}:null,p=m.__gwtStatsSessionId?m.__gwtStatsSessionId:null,q,r,s,t=T,u={},v=[],w=[],A=[],B=0,C,D;o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:W,millis:(new Date).getTime(),type:X});if(!m.__gwt_stylesLoaded){m.__gwt_stylesLoaded={}}if(!m.__gwt_scriptsLoaded){m.__gwt_scriptsLoaded={}}function F(){var b=false;try{var c=m.location.search;return (c.indexOf(Y)!=-1||(c.indexOf(Z)!=-1||m.external&&m.external.gwtOnLoad))&&c.indexOf($)==-1}catch(a){}F=function(){return b};return b}
function G(){if(q&&r){var b=n.getElementById(U);var c=b.contentWindow;if(F()){c.__gwt_getProperty=function(a){return L(a)}}Hdm_rms=null;c.gwtOnLoad(C,U,t,B);o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:_,millis:(new Date).getTime(),type:ab})}}
function H(){function e(a){var b=a.lastIndexOf(bb);if(b==-1){b=a.length}var c=a.indexOf(cb);if(c==-1){c=a.length}var d=a.lastIndexOf(db,Math.min(c,b));return d>=0?a.substring(0,d+1):T}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=n.createElement(eb);b.src=a+fb;a=e(b.src)}return a}
function g(){var a=J(gb);if(a!=null){return a}return T}
function h(){var a=n.getElementsByTagName(hb);for(var b=0;b<a.length;++b){if(a[b].src.indexOf(ib)!=-1){return e(a[b].src)}}return T}
function i(){var a;if(typeof isBodyLoaded==jb||!isBodyLoaded()){var b=kb;var c;n.write(lb+b+mb);c=n.getElementById(b);a=c&&c.previousSibling;while(a&&a.tagName!=nb){a=a.previousSibling}if(c){c.parentNode.removeChild(c)}if(a&&a.src){return e(a.src)}}return T}
function j(){var a=n.getElementsByTagName(ob);if(a.length>0){return a[a.length-1].href}return T}
function k(){var a=n.location;return a.href==a.protocol+pb+a.host+a.pathname+a.search+a.hash}
var l=g();if(l==T){l=h()}if(l==T){l=i()}if(l==T){l=j()}if(l==T&&k()){l=e(n.location.href)}l=f(l);t=l;return l}
function I(){var b=document.getElementsByTagName(qb);for(var c=0,d=b.length;c<d;++c){var e=b[c],f=e.getAttribute(rb),g;if(f){f=f.replace(sb,T);if(f.indexOf(tb)>=0){continue}if(f==ub){g=e.getAttribute(vb);if(g){var h,i=g.indexOf(wb);if(i>=0){f=g.substring(0,i);h=g.substring(i+1)}else{f=g;h=T}u[f]=h}}else if(f==xb){g=e.getAttribute(vb);if(g){try{D=eval(g)}catch(a){alert(yb+g+zb)}}}else if(f==Ab){g=e.getAttribute(vb);if(g){try{C=eval(g)}catch(a){alert(yb+g+Bb)}}}}}}
function J(a){var b=u[a];return b==null?null:b}
function K(a,b){var c=A;for(var d=0,e=a.length-1;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
function L(a){var b=w[a](),c=v[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(D){D(a,d,b)}throw null}
var M;function N(){if(!M){M=true;var a=n.createElement(Cb);a.src=Db;a.id=U;a.style.cssText=Eb;a.tabIndex=-1;n.body.appendChild(a);o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:_,millis:(new Date).getTime(),type:Fb});a.contentWindow.location.replace(t+P)}}
w[Gb]=function(){var b=navigator.userAgent.toLowerCase();var c=function(a){return parseInt(a[1])*1000+parseInt(a[2])};if(function(){return b.indexOf(Hb)!=-1}())return Ib;if(function(){return b.indexOf(Jb)!=-1&&n.documentMode>=10}())return Kb;if(function(){return b.indexOf(Jb)!=-1&&n.documentMode>=9}())return Lb;if(function(){return b.indexOf(Jb)!=-1&&n.documentMode>=8}())return Mb;if(function(){return b.indexOf(Nb)!=-1}())return Ob;return Pb};v[Gb]={gecko1_8:0,ie10:1,ie8:2,ie9:3,safari:4};Hdm_rms.onScriptLoad=function(){if(M){r=true;G()}};Hdm_rms.onInjectionDone=function(){q=true;o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:Qb,millis:(new Date).getTime(),type:ab});G()};I();H();var O;var P;if(F()){if(m.external&&(m.external.initModule&&m.external.initModule(U))){m.location.reload();return}P=Rb;O=T}o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:W,millis:(new Date).getTime(),type:Sb});if(!F()){try{K([Kb],Tb);K([Ob],Ub);K([Ib],Vb);K([Lb],Wb);K([Mb],Xb);O=A[L(Gb)];var Q=O.indexOf(Yb);if(Q!=-1){B=Number(O.substring(Q+1));O=O.substring(0,Q)}P=O+Zb}catch(a){return}}var R;function S(){if(!s){s=true;if(!__gwt_stylesLoaded[$b]){var a=n.createElement(_b);__gwt_stylesLoaded[$b]=a;a.setAttribute(ac,bc);a.setAttribute(cc,t+$b);n.getElementsByTagName(dc)[0].appendChild(a)}G();if(n.removeEventListener){n.removeEventListener(ec,S,false)}if(R){clearInterval(R)}}}
if(n.addEventListener){n.addEventListener(ec,function(){N();S()},false)}var R=setInterval(function(){if(/loaded|complete/.test(n.readyState)){N();S()}},50);o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:W,millis:(new Date).getTime(),type:ab});o&&o({moduleName:U,sessionId:p,subSystem:V,evtGroup:Qb,millis:(new Date).getTime(),type:X});n.write(fc)}
Hdm_rms();