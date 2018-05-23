public class OrderedPair{
    private BTreeNode _abscissa;
    private int _ordinate;

    public OrderedPair(BTreeNode _abscissa, int _ordinate) {
        this._abscissa = _abscissa;
        this._ordinate = _ordinate;
    }

    public BTreeNode get_abscissa() {
        return _abscissa;
    }

    public void set_abscissa(BTreeNode _abscissa) {
        this._abscissa = _abscissa;
    }

    public int get_ordinate() {
        return _ordinate;
    }

    public void set_ordinate(int _ordinate) {
        this._ordinate = _ordinate;
    }
}
