package com.example.artem.Home;

import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artem.Home.utility.ArtistAdapter;
import com.example.artem.R;
import com.example.artem.databinding.FragmentArtistBinding;

import java.util.HashMap;

public class ArtistFragment extends Fragment {

    FragmentArtistBinding binding;
    ArtistAdapter adapter;
    HashMap<String, String> map;
    float Px;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentArtistBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data();
        start_connection();

    }

    private void data(){
        map = new HashMap<>();
        map.put("Caravaggio","https://theartwolf.com/wp-content/uploads/2021/06/Caravaggio.jpg");
        map.put("Monet","https://theartwolf.com/wp-content/uploads/2021/06/Claude_Monet_-_1899.jpg");
        map.put("Rembrandt","https://theartwolf.com/wp-content/uploads/2021/06/Rembrandt_van_Rijn_-_Self-Portrait_-_1659.jpg");
        map.put("picasso","https://theartwolf.com/wp-content/uploads/2021/06/Pablo_Picasso_photo_bn.jpg");
        map.put("Turner","https://theartwolf.com/wp-content/uploads/2021/06/William_Turner_-_selfportrait.jpg");
        map.put("gitto","https://theartwolf.com/wp-content/uploads/2021/06/Giotto_di_Bondone_portrait.jpg");
        map.put("leonardo","https://theartwolf.com/wp-content/uploads/2021/06/Leonardo_da_Vinci_portrait.jpg");
        map.put("cezanne","https://theartwolf.com/wp-content/uploads/2021/06/Paul_Cezanne_-_Self-portrait_-_1879-80.jpg");
        map.put("van eyck","https://theartwolf.com/wp-content/uploads/2021/06/Jan_van_Eyck_-_Portrait_of_a_Man_in_a_Turban_-_1433.jpg");
        map.put("Duner","https://theartwolf.com/wp-content/uploads/2021/06/Albrecht_Durer_-_Self-portrait.jpg");
        map.put("Michelangelo","https://theartwolf.com/wp-content/uploads/2021/06/Michelangelo_Buonarroti_-_1545.jpg");
        map.put("Goya","https://theartwolf.com/wp-content/uploads/2021/06/Francisco_de_Goya_por-Vicente_Lopez_Portana-_1826_-_94_x_78_cm_-_Prado_Museum_-_Madrid.jpg");
        map.put("Van Gogh","https://theartwolf.com/wp-content/uploads/2021/06/Vincent_van_Gogh_-_Self-Portrait_-_1887_-_42_x_33.7_cm_-_Art_Institute_of_Chicago.jpg");
        map.put("Manet","https://theartwolf.com/wp-content/uploads/2021/06/Edouard_Manet_-_1832-1883.jpg");
        map.put("Mantisse","https://theartwolf.com/wp-content/uploads/2021/06/Henri_Matisse_-_1869-1954.jpg");
        map.put("Raphael","https://theartwolf.com/wp-content/uploads/2021/06/Raffaello_Sanzio_-_1483-1520.jpg");
        map.put("El Greco","https://theartwolf.com/wp-content/uploads/2021/06/Domenikos_Theotokopoulos_-_El_Greco_-_1541-1614.jpg");
        map.put("Gauguin","https://theartwolf.com/wp-content/uploads/2021/06/Paul_Gauguin_-_Portrait_de_lartiste_au_Christ_jaune_-_1890_-_Oil_on_canvas_-_38_x_46_cm_-_Musee_dOrsay_-_Paris.jpg");
        map.put("Basquiat","https://theartwolf.com/wp-content/uploads/2021/06/Jean-Michel_Basquiat_-_1960-1988.jpg");
        map.put("Munch","https://theartwolf.com/wp-content/uploads/2021/06/Edvard_Munch_-_Self-portrait_with_cigarette_-_1895_-_Oil_on_canvas_-_National_Museum_of_Art_Architecture_and_Design.jpg");
        map.put("Titian","https://theartwolf.com/wp-content/uploads/2021/06/Titian_-_Self-portrait_-_1546-1547_-_Oil_on_canvas_-_96_x_75_cm_-_Gemaldegalerie_-_Berlin.jpg");
        map.put("Bacon","https://theartwolf.com/wp-content/uploads/2021/06/Francis_Bacon_-_by_Gray_-_1909-1992.jpg");
        map.put("Wharhol","https://theartwolf.com/wp-content/uploads/2021/06/Andy_Warhol_-_Stockholm_1968.jpg");
        map.put("Rubens","https://theartwolf.com/wp-content/uploads/2021/06/Sir_Peter_Paul_Rubens_-_Portrait_of_the_Artist_-_1577-1640.jpg");
        map.put("Vermeer","https://theartwolf.com/wp-content/uploads/2021/06/Johannes_Vermeer_-_De_koppelaarster_-_1656_-_Oil_on_canvas_-_143_x_130_cm.jpg");
        map.put("Artemisia","https://theartwolf.com/wp-content/uploads/2021/06/Artemisia_Gentileschi_-_Self-portrait_as_the_Allegory_of_Painting_La_Pittura_-_1593-1653.jpg");
        map.put("Miro","https://theartwolf.com/wp-content/uploads/2021/06/Joan_Miro_-_Self-portrait_-_1919_-_Musee_Picasso.jpg");
        map.put("Chagall","https://theartwolf.com/wp-content/uploads/2021/06/Marc-Chagall_-_1887-1985.jpg");
        map.put("Piero","https://theartwolf.com/wp-content/uploads/2021/06/Piero_della_Francesca_-_1415-1492.jpg");
        map.put("Mondrian","https://theartwolf.com/wp-content/uploads/2021/06/Piet_Mondrian_-_Self-portrait_-_1900.jpg");
        map.put("Courbet","https://theartwolf.com/wp-content/uploads/2021/06/Gustave_Courbet_-_Le_Desespere_-_1843.jpg");
        map.put("Poussin","https://theartwolf.com/wp-content/uploads/2021/06/Nicolas_Poussin_-_Self-portrait_-_1594-1665.jpg");
        map.put("Klimt","https://theartwolf.com/wp-content/uploads/2021/06/Gustav_Klimt.jpg");
        map.put("Delacroix","https://theartwolf.com/wp-content/uploads/2021/06/Eugene_Delacroix_-_Portrait_de_lartiste_ca.1837_-_1798-1863_-_Louvre_-_Paris.jpg");
        map.put("Uccello","https://theartwolf.com/wp-content/uploads/2021/06/Paolo_Uccello_-_1397-1475_-_Louvre_-_Paris.jpg");
        map.put("Blake","https://theartwolf.com/wp-content/uploads/2021/06/William_Blake_-_1770-1845.jpg");
        map.put("Friedrich","https://theartwolf.com/wp-content/uploads/2021/06/Caspar_David__Friedrich_-_1774-1840.jpg");
        map.put("Malevich","https://theartwolf.com/wp-content/uploads/2021/06/Kazimir_Malevich_-_Self-Portrait_-_1878-1935.jpg");
        map.put("De Kooning","https://theartwolf.com/wp-content/uploads/2021/06/Willem_de_Kooning_in_his_studio_-_1904-1997.jpg");
        map.put("Homer","https://theartwolf.com/wp-content/uploads/2021/06/Winslow_Homer_-_1836-1910.jpg");
        map.put("Richter","https://theartwolf.com/wp-content/uploads/2021/06/Gerhard_Richter_-_Prague_-_2017.jpg");
        map.put("Duchamp","https://theartwolf.com/wp-content/uploads/2021/06/Marcel_Duchamp_-_1887-1968_-_Yale_University_Art_Gallery.jpg");
        map.put("Botticelli","https://theartwolf.com/wp-content/uploads/2021/06/Sandro_Botticelli_-_1445-1510.jpg");
        map.put("Frida kahlo","https://theartwolf.com/wp-content/uploads/2021/06/Frida_Kahlo_-_1907-1954.jpg");
        map.put("Hopper","https://theartwolf.com/wp-content/uploads/2021/06/Edward_Hopper_-_1882-1967.jpg");
        map.put("Rothko","https://theartwolf.com/wp-content/uploads/2021/06/Mark_Rothko_-_Painter_Denisova_Olesya_Alexandrovna.jpg");
        map.put("Mantegna","https://theartwolf.com/wp-content/uploads/2021/06/Andrea_Mantegna_-_detail_possible_self-portrait.jpg");
        map.put("Klee","https://theartwolf.com/wp-content/uploads/2021/06/Paul_Klee_-_Senecio_Head_of_a_man_-_1879-1940.jpg");
        map.put("Hans Holbein","https://theartwolf.com/wp-content/uploads/2021/06/Hans_Holbein_the_Younger_-_Self-portrait_-_1497-1543.jpg");
        map.put("Degas","https://theartwolf.com/wp-content/uploads/2021/06/Edgar_Degas_-_self_portrait_photograph_1895_-_1834-1917.jpg");
        map.put("Fra Andelico","https://theartwolf.com/wp-content/uploads/2021/06/Fra_Angelico_-_1395-1455.jpg");
        map.put("Seurat","https://theartwolf.com/wp-content/uploads/2021/06/Georges_Seurat_-_1888.jpg");
        map.put("Watteau","https://theartwolf.com/wp-content/uploads/2021/06/Antoine_Watteau_-_1684-1721.jpg");
        map.put("Hockney","https://theartwolf.com/wp-content/uploads/2021/06/David_Hockney_-_2017_at_Flash_Expo.jpg");
        map.put("Ernst","https://theartwolf.com/wp-content/uploads/2021/06/Max_Ernst_-_1968.jpg");
        map.put("TinToretto","https://theartwolf.com/wp-content/uploads/2021/06/Tintoretto_-_Self-portrait_-_1518-1594.jpg");
        map.put("Johns","https://theartwolf.com/wp-content/uploads/2021/06/Jasper_Johns_-_1930-present.jpg");
        map.put("Boccioni","https://theartwolf.com/wp-content/uploads/2021/06/Umberto_Boccioni_-_Self-portrait_oil_on_canvas_1905_Metropolitan_Museum_of_Art.jpg");
        map.put("Duccio","https://theartwolf.com/wp-content/uploads/2021/06/Duccio_di_Buoninsegna_-_1255-1319.jpg");
        map.put("Van der Weyden","https://theartwolf.com/wp-content/uploads/2021/06/Rogier_van_der_Weyden_-_1399-1464.jpg");
        map.put("Constable","https://theartwolf.com/wp-content/uploads/2021/06/John_Constable_-_1800-1837.jpg");
        map.put("David","https://theartwolf.com/wp-content/uploads/2021/06/Jacques-Louis_David_-_Self-portrait_-_1748-1825.jpg");
        map.put("Gorky","https://theartwolf.com/wp-content/uploads/2021/06/Arshile_Gorky_-_1904-1948.jpg");
        map.put("Giorgione","https://theartwolf.com/wp-content/uploads/2021/06/Giorgio_Barbarelli_da_Castelfranco_-_Giorgione_-_Self-portrait_as_David_-_1478-1510.jpg");
        map.put("The Bosch","https://theartwolf.com/wp-content/uploads/2021/06/Hieronymus_Bosch_-_1533-1578.jpg");
        map.put("Pieter Bruegel","https://theartwolf.com/wp-content/uploads/2021/06/Pieter_Bruegel_the_Elder_-_The_Painter_and_the_Buyer_1565_-_1526-1569.jpg");
        map.put("Simone","https://theartwolf.com/wp-content/uploads/2021/06/Simone_Martini_-_Ritratto_-_1284-1344.jpg");
        map.put("Franz","https://theartwolf.com/wp-content/uploads/2021/06/Franz_Marc_-_1880-1916.jpg");
        map.put("Renoir","https://theartwolf.com/wp-content/uploads/2021/06/Pierre-Auguste_Renoir_-_Self-portrait_-_1876_-_1841-1919.jpg");
        map.put("Gericault","https://theartwolf.com/wp-content/uploads/2021/06/Theodore_Gericault_-_Portrait_of_a_Kleptomaniac_-_1791-1824.jpg");
        map.put("Hogarth","https://theartwolf.com/wp-content/uploads/2021/06/William_Hogarth_-_Self-Portrait_-_1697-1764.jpg");
        map.put("Cammille Carot","https://theartwolf.com/wp-content/uploads/2021/06/Jean-Baptiste_Camille_Corot_-_Self-portrait_-_1796-1875.jpg");
        map.put("Braque","https://theartwolf.com/wp-content/uploads/2021/06/Georges_Braque_-_1908_-_1882-1963.jpg");
        map.put("Morisot","https://theartwolf.com/wp-content/uploads/2021/06/Berthe_Marie_Pauline_Morisot_-_Self-portrait_-_1841-1895.jpg");
        map.put("Whistler","https://theartwolf.com/wp-content/uploads/2021/06/James_Abbot_McNeill_Whistler_-_Selbstportrat_-_1834-1903.jpg");
        map.put("Church","https://theartwolf.com/wp-content/uploads/2021/06/Frederic_Edwin_Church_-_1826-1900.jpg");
        map.put("De la tour","https://theartwolf.com/wp-content/uploads/2021/06/Georges_de_La_Tour_-_1593-1652.jpg");
        map.put("Millet","https://theartwolf.com/wp-content/uploads/2021/06/Jean-Francois_Millet_-_1814-1875.jpg");
        map.put("Modigliani","https://theartwolf.com/wp-content/uploads/2021/06/Amedeo_Modigliani_-_1918_-_1884-1920.jpg");
        map.put("Vigee le brun","https://theartwolf.com/wp-content/uploads/2021/06/Elisabeth-Louise_Vigee-Lebrun_-_Self-portrait_-_1755-1842.jpg");
        map.put("Migritte","https://theartwolf.com/wp-content/uploads/2021/06/Rene_Magritte_-_1898-1967.jpg");
        map.put("Cimabue","https://theartwolf.com/wp-content/uploads/2021/06/Cimabue_-_1240-1302.jpg");
        map.put("Ensor","https://theartwolf.com/wp-content/uploads/2021/06/James_Ensor_-_1860-1949.jpg");
        map.put("Schiele","https://theartwolf.com/wp-content/uploads/2021/06/Egon_Schiele_-_Self-Portrait_with_Physalis_-_1912_-_1890-1918.jpg");
        map.put("Rossetti","https://theartwolf.com/wp-content/uploads/2021/06/Dante_Gabriel_Rossetti_-_1828-1882_-_by_George_Frederic_Watts.jpg");
        map.put("Hals","https://theartwolf.com/wp-content/uploads/2021/06/Frans_Hals_-_Self-Portrait_-_1580-1666.jpg");
        map.put("Lorrian","https://theartwolf.com/wp-content/uploads/2021/06/Claude_Lorrain_-_1600-1682.jpg");
        map.put("Lichtenstein","https://theartwolf.com/wp-content/uploads/2021/06/Roy_Lichtenstein_-_1923-1997.jpg");
        map.put("Zurbaran","https://theartwolf.com/wp-content/uploads/2021/06/Francisco_de_Zurbaran_-_Self-portrait_-_1598-1664.jpg");
        map.put("Okeefe","https://theartwolf.com/wp-content/uploads/2021/06/Georgia_OKeeffe_-_1887-1986.jpg");
        map.put("Banksy","https://theartwolf.com/wp-content/uploads/2021/06/Banksy_-_unknown.jpg");
        map.put("Bouguereau","https://theartwolf.com/wp-content/uploads/2021/06/William_Bouguereau_-_Self-portrait_-_1825-1905.jpg");
        map.put("Moreau","https://theartwolf.com/wp-content/uploads/2021/06/Gustave_Moreau_-_1826-1898.jpg");
        map.put("De Chirico","https://theartwolf.com/wp-content/uploads/2021/06/Giorgio_de_Chirico_-_1888-1978.jpg");
        map.put("Leger","https://theartwolf.com/wp-content/uploads/2021/06/Fernand_Leger_-_1881-1955.jpg");
        map.put("Ingres","https://theartwolf.com/wp-content/uploads/2021/06/Jean-Auguste-Dominique_Ingres_-_Self-portrait_-_1780-1867.jpg");


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void start_connection(){
        ConnectivityManager connectivityManager =this.getContext().getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            adapter=new ArtistAdapter(map, this.getActivity(), ArtistFragment.this);
            binding.artistRecycleView.setAdapter(adapter);
            RecyclerView.LayoutManager gridLayoutManager=new GridLayoutManager(this.getActivity(),2);
            binding.artistRecycleView.setLayoutManager(gridLayoutManager);
            addBottomOffset();
        } else {
            Toast.makeText(getActivity(), "no internet connection ", Toast.LENGTH_SHORT).show();
        }
    }
    public void addBottomOffset(){
        Px=getResources().getDimension(R.dimen.bottom_offset);
        BottomOffsetDecoration bottomOffsetDecoration=new BottomOffsetDecoration((int) Px);
        binding.artistRecycleView.addItemDecoration(bottomOffsetDecoration);
    }
    static class BottomOffsetDecoration extends RecyclerView.ItemDecoration{
        private int mBottomOffset;
        public BottomOffsetDecoration(int bottomOffset) {
            mBottomOffset = bottomOffset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int dataSize = state.getItemCount();
            int position = parent.getChildAdapterPosition(view);
            if (dataSize > 0 && position == dataSize - 1) {
                outRect.set(0, 0, 0, mBottomOffset);
            } else {
                outRect.set(0, 0, 0, 0);
            }

        }
    }
}