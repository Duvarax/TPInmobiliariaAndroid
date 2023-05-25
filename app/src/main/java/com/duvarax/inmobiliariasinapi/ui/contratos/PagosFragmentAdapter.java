package com.duvarax.inmobiliariasinapi.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.duvarax.inmobiliariasinapi.R;
import com.duvarax.inmobiliariasinapi.modelo.Pago;

import org.w3c.dom.Text;

import java.util.List;

public class PagosFragmentAdapter extends RecyclerView.Adapter<PagosFragmentAdapter.ViewHolder> {

    private Context context;
    private List<Pago> listaPagos;
    private LayoutInflater inflater;

    public PagosFragmentAdapter(Context context, List<Pago> listaPagos, LayoutInflater inflater) {
        this.context = context;
        this.listaPagos = listaPagos;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public PagosFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View root = inflater.inflate(R.layout.pagos_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosFragmentAdapter.ViewHolder holder, int position) {
        holder.codigoContrato.setText(listaPagos.get(position).getcontratoId()+"");
        holder.importe.setText(listaPagos.get(position).getImporte()+"");
        holder.fechaPago.setText(listaPagos.get(position).getfechaPago());
        holder.identificadorPagoPago.setText(listaPagos.get(position).getidentificadorPago()+"");
        holder.codigoPago.setText(listaPagos.get(position).getid()+"");
    }

    @Override
    public int getItemCount() {
        return listaPagos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView codigoPago;
        TextView identificadorPagoPago;
        TextView codigoContrato;
        TextView importe;
        TextView fechaPago;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoPago = itemView.findViewById(R.id.tvCodigoPago);
            identificadorPagoPago = itemView.findViewById(R.id.tvidentificadorPagoPago);
            codigoContrato = itemView.findViewById(R.id.tvCodigoContrato);
            importe = itemView.findViewById(R.id.tvImporteContrato);
            fechaPago = itemView.findViewById(R.id.tvFechaPago);

        }
    }
}

