package saefulProjectSpringBoot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saefulProjectSpringBoot.model.TabunganModel;
import saefulProjectSpringBoot.repository.TabunganRepository;

@Service
public class TabunganDao {
	@Autowired
	TabunganRepository tabunganrepositori;
	
	public List<TabunganModel> getAll() {
		return tabunganrepositori.findAll();
	}
	public int getxaldo() {
		List<TabunganModel> b= tabunganrepositori.findAll();
		int x=b.get(b.size()-1).getSaldo();
		return x;
	}
	public TabunganModel getsatu(Long id) {
		return tabunganrepositori.findOne(id);
	}

	public List<TabunganModel> getnik(String nik) {
		return tabunganrepositori.getnik(nik);
	}
	public TabunganModel saveTabungan(TabunganModel book) {
		return tabunganrepositori.save(book);
	}
	public void tabunganDelete(Long id) {
		tabunganrepositori.delete(id);
	}
}
