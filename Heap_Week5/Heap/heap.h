#ifndef HEAP_H
#define HEAP_H

#include<stdlib.h>

using namespace std;

class Heap
{
    int* arr;
    int heapSize;

public:
    Heap(int n)
    {
        arr = new int[n];
        heapSize = 0;
    }
    void insert(int i)
    {
        if (this->isEmpty())
        {
            arr[0] = i;
            heapSize++;
            return;
        }
        arr[heapSize] = i;
        bubbleUp(heapSize);
        heapSize++;
    }
    void deleteKey()
    {
        arr[0] = arr[heapSize-1];
        arr[heapSize-1] = 0;
        heapSize--;
        bubbleDown();
    }
    int getMax()
    {
        return arr[0];
    }
    int size()
    {
        return this->heapSize;
    }
    /** Default destructor */
    ~Heap()
    {
        delete[] arr;
        heapSize = 0;
    }

protected:

private:
    bool isEmpty()
    {
        if (this->heapSize == 0)
            return true;
        return false;
    }
    void bubbleUp(int i)
    {
        int parentIndex = (i-1)/2;
        int childIndex = i;
        while (parentIndex >=0 )
        {
            if (arr[childIndex] > arr[parentIndex])
            {
                int temp = arr[childIndex];
                arr[childIndex] = arr[parentIndex];
                arr[parentIndex] = temp;
                childIndex = parentIndex;
                parentIndex = (parentIndex-1)/2;
            }
            else
            {
                break;
            }
        }
    }
    void bubbleDown()
    {
        int parentIndex = 0;
        while ((parentIndex*2+1) < heapSize || (parentIndex*2+2) < heapSize)
        {
            if (arr[parentIndex] > arr[(2*parentIndex) + 2] && arr[parentIndex] > arr[(2*parentIndex) + 1])
            {
                break;
            }
            if (arr[parentIndex] < arr[(2*parentIndex) + 2] && arr[parentIndex] < arr[(2*parentIndex) + 1])
            {
                if (arr[(2*parentIndex) + 2] < arr[(2*parentIndex) + 1])
                {
                    int temp = arr[parentIndex];
                    arr[parentIndex] = arr[(2*parentIndex) + 1];
                    arr[(2*parentIndex) + 1] = temp;
                    parentIndex = (2*parentIndex) + 1;
                }
                else if(arr[(2*parentIndex) + 2] >= arr[(2*parentIndex) + 1])
                {
                    int temp = arr[parentIndex];
                    arr[parentIndex] = arr[(2*parentIndex) + 2];
                    arr[(2*parentIndex) + 2] = temp;
                    parentIndex = (2*parentIndex) + 2;
                }
            }
            else if(arr[parentIndex] < arr[(2*parentIndex) + 1])
            {
                int temp = arr[parentIndex];
                arr[parentIndex] = arr[(2*parentIndex) + 1];
                arr[(2*parentIndex) + 1] = temp;
                parentIndex = (2*parentIndex) + 1;
            }
            else if (arr[parentIndex] < arr[(2*parentIndex) + 2])
            {
                int temp = arr[parentIndex];
                arr[parentIndex] = arr[(2*parentIndex) + 2];
                arr[(2*parentIndex) + 2] = temp;
                parentIndex = (2*parentIndex) + 2;
            }
            else
            {
                break;
            }
        }
    }
};

void heapsort(vector<int>&v)
{
    int sizeOfVector = v.size();
    Heap h(sizeOfVector);
    for (int i = 0; i < sizeOfVector; i++)
    {
        h.insert(v[i]);
    }
    for (int j = 0; j < sizeOfVector; j++)
    {
        v[j] = h.getMax();
        h.deleteKey();
    }
}

#endif // HEAP_H
