define(function(C, H, A) {
    var L = {},
    D = document.documentElement,
    K = "modernizr",
    F = function(T, V, O, U) {
        var N, S, Q, M = document.createElement("div"),
        R = document.body,
        P = R ? R: document.createElement("body");
        if (parseInt(O, 10)) {
            while (O--) {
                Q = document.createElement("div");
                Q.id = U ? U[O] : K + (O + 1);
                M.appendChild(Q)
            }
        }
        N = ["&#173;", '<style id="s', K, '">', T, "</style>"].join("");
        M.id = K; (R ? M: P).innerHTML += N;
        P.appendChild(M);
        if (!R) {
            P.style.background = "";
            D.appendChild(P)
        }
        S = V(M, T); ! R ? P.parentNode.removeChild(P) : M.parentNode.removeChild(M);
        return !! S
    };
    var G = "Webkit Moz O ms".split(" "),
    E = D.style;
    function I(N, M) {
        return typeof N === M
    }
    function J(O, M) {
        for (var N in O) {
            if (E[O[N]] !== undefined) {
                return M == "pfx" ? O[N] : true
            }
        }
        return false
    }
    function B(Q, M, P) {
        var N = Q.charAt(0).toUpperCase() + Q.substr(1),
        O = (Q + " " + G.join(N + " ") + N).split(" ");
        if (I(M, "string") || I(M, "undefined")) {
            return J(O, M)
        } else {}
    }
    L.hasTransform = function() {
        return !! B("transform")
    };
    L.has3d = function() {
        var M = !!B("perspective");
        if (M && "webkitPerspective" in D.style) {
            F("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}",
            function(N, O) {
                M = N.offsetLeft === 9 && N.offsetHeight === 3
            })
        }
        return M
    };
    return L
});