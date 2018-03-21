package Matrix;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows*cols];
    }

    Matrix(double[][] d){
        this.rows = d.length;
        int cols = 0;
        for(int i=0 ; i<d.length ; ++i)
            cols = Math.max(cols,d[i].length);
        this.cols = cols;
        int r = 0;
        int c = 0;
        data = new double[this.rows*this.cols];
        for(int i=0 ; i<this.rows*this.cols ; ++i){
            if(d[r].length<=c)
                data[i] = 0;
            else
                data[i] = d[r][c];
            if(c+1 == this.cols){
                c = 0;
                r++;
            }
            else
                c++;
        }
    }

    double[][] asArray(){
       double[][] wynik = new double[this.rows][this.cols];
       int r = 0;
       int c = 0;
       for(double i:data){
           wynik[r][c] = i;
           if(c+1 ==this.cols){
               c = 0;
               r++;
           }
           else
               c++;
       }
       for(int i=0 ; i<wynik.length ; ++i){
           for(int j=0 ; j<wynik[i].length ; j++)
               System.out.print(wynik[i][j] + " ");
           System.out.println();
       }
       return wynik;
    }

    double get(int r, int c){
        return data[(r-1)*this.cols + c-1];
    }

    void set(int r, int c, double value){
        data[(r-1)*this.cols + c-1] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0 ; i<this.rows ; i++){
            buf.append("[");
            for(int j=0 ; j<this.cols ; j++){
                buf.append("  ");
                buf.append(this.get(i+1,j+1));
                buf.append("  ");
            }
            buf.append("]\n");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows, int newCols){
        if(data.length != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",this.rows,this.cols,newRows,newCols));

        this.rows= newRows;
        this.cols = newCols;

    }

    int[] shape(){
        int[] result = new int[2];
        result[0] = this.rows;
        result[1] = this.cols;
        return result;
    }

    Matrix add(Matrix m){
        if(this.rows != m.rows && this.cols != m.cols)
            throw new RuntimeException(String.format("Shape of matrix is not correct"));
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            result.data[i] = this.data[i] + m.data[i];
        }
        return result;
    }

    Matrix sub(Matrix m){
        if(this.rows != m.rows && this.cols != m.cols)
            throw new RuntimeException(String.format("Shape of matrix is not correct"));
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            result.data[i] = this.data[i] - m.data[i];
        }
        return result;
    }

    Matrix mul(Matrix m){
        if(this.rows != m.rows && this.cols != m.cols)
            throw new RuntimeException(String.format("Shape of matrix is not correct"));
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            result.data[i] = this.data[i] * m.data[i];
        }
        return result;
    }

    Matrix div(Matrix m){
        if(this.rows != m.rows && this.cols != m.cols)
            throw new RuntimeException(String.format("Shape of matrix is not correct"));
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            if(m.data[i]==0)
                throw new RuntimeException(String.format("Dzielenie przez 0"));
            result.data[i] = this.data[i] / m.data[i];
        }
        return result;
    }

    Matrix dot(Matrix m){
        if(this.cols!=m.rows)
            throw new RuntimeException(String.format("Shape of matrix is not correct"));
        Matrix result = new Matrix(this.rows,m.cols);
        int r = 1;
        int c = 1;
        for(int i=1 ; i<=result.rows ; ++i){
            for(int j=1 ; j<=result.cols ; ++j){
                double temp = 0;
                for(int k=1 ; k<=this.cols ; ++k){
                    temp = temp + this.get(r,k)*m.get(k,c);
                }
                result.set(i,j,temp);
                c++;
            }
            r++;
            c = 1;
        }
        return result;
    }

    Matrix add(double w){
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            result.data[i] = this.data[i] + w;
        }
        return result;
    }

    Matrix sub(double w){
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            result.data[i] = this.data[i] - w;
        }
        return result;
    }

    Matrix mul(double w){
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            result.data[i] = this.data[i] * w;
        }
        return result;
    }
    Matrix div(double w){
        if(w==0)
            throw new RuntimeException(String.format("Dzielenie przez 0"));
        Matrix result = new Matrix(this.rows,this.cols);
        for(int i=0 ; i<data.length ; ++i){
            result.data[i] = this.data[i] / w;
        }
        return result;
    }

    double frobenius(){
        double result = 0;
        for (int i=0 ; i<this.rows*this.cols ; ++i){
            result = result + Math.pow(this.data[i],2);
        }
        return Math.sqrt(result);
    }

    ////////////////// GRUPA B ///////////////////////////////////


    Matrix getTransposition(){
        Matrix result = new Matrix(this.cols,this.rows);
        //Wykorzystuje metody napisane wczesniej: set do pobrania wartosci i get do ustawienia wartosci w nowej macierzy
        for(int i=0 ; i<this.rows ; ++i){
            for(int j = 0 ; j<this.cols ; j++ ){
                result.set(j+1,i+1,this.get(i+1,j+1));
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////
}
