function styleMaxMin(isMax) {
    isMax = isMax == undefined ? true : isMax;
    var sortedTbl =
        $('#table tbody td:last-child').sort(isMax ?
            (a, b) => {return +b.textContent - +a.textContent} :
    (a, b) => {return +a.textContent - +b.textContent});
    sortedTbl.filter(function (idx, ele) {
        var currVal = +ele.textContent;
        return currVal == +sortedTbl.eq(0).text();
    }).css('color', 'red');
}

$(function () {
    styleMaxMin();
    $('#table').on('post-body.bs.table', function (e, data) {
        styleMaxMin();
    });
});