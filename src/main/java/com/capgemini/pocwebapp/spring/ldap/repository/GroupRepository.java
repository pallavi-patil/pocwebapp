package com.capgemini.pocwebapp.spring.ldap.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import com.capgemini.pocwebapp.beans.LdapGroup;
import com.capgemini.pocwebapp.beans.LdapUser;

import javax.naming.Name;
import javax.naming.ldap.LdapName;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class GroupRepository implements BaseLdapNameAware {

    @Autowired
    private LdapTemplate ldapTemplate;
    private LdapName baseLdapPath;

    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }

    public List<LdapGroup> findAll(){
        return ldapTemplate.search(query().where("objectClass").is("groupOfUniqueNames"),
               new GroupContextMapper());
    	/*DistinguishedName dn = new DistinguishedName();
		dn.add("ou","People");
		dn.add("cn","ADMIN");
		return (List<LdapGroup>) ldapTemplate.lookup(dn, 
			new ContextMapper() {
				public Object mapFromContext(Object ctx) {
					LdapGroup group = new LdapGroup();
                    DirContextAdapter adapter  = (DirContextAdapter) ctx;
                    System.out.println(" memberuid : "+adapter.getStringAttributes("uniqueMember"));
                    System.out.println(" cn : "+adapter.getStringAttributes("cn"));            
                    Object [] uid = adapter.getStringAttributes("uniqueMember");
                    String [] cn = adapter.getStringAttributes("cn");
                    group.setName(cn[0]);
                    System.out.println(cn[0]);
                    System.out.println(uid[0]);
                    String uniqueMember = String.valueOf(uid[0]);
                     String[] splittedString = uniqueMember.split(",");
                    System.out.println(" Splitted String "+splittedString[0]);
                    String uidKey = splittedString[0];
                    String[] uidSplit = uidKey.split("=");
                    System.out.println(" uidSplit[1] "+uidSplit[1]);
                    String uidValue = uidSplit[1];                    
                    Name memberDn = LdapNameBuilder.newInstance(uniqueMember).build();
                    group.addMember(memberDn);
					return group;
				}
			});*/
    }

    public void addMemberToGroup(String groupName, LdapUser p) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildPersonDn(p);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue("uniqueMember", personDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    public void removeMemberFromGroup(String groupName, LdapUser p) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildPersonDn(p);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue("uniqueMember", personDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance(baseLdapPath)
                .add("ou", "groups")
                .add("cn", groupName)
                .build();
    }

    private Name buildPersonDn(LdapUser person) {
        return LdapNameBuilder.newInstance(baseLdapPath)
                .add("ou", "People")
                .add("uid", person.getUid())
                .build();
    }

    private static class GroupContextMapper extends AbstractContextMapper<LdapGroup> {
        public LdapGroup doMapFromContext(DirContextOperations context) {
        	LdapGroup group = new LdapGroup();
            group.setName(context.getStringAttribute("cn"));
            Object[] members = context.getObjectAttributes("uniqueMember");
            for (Object member : members){
                Name memberDn = LdapNameBuilder.newInstance(String.valueOf(member)).build();
                group.addMember(memberDn);
            }
            System.out.println("Group Data :"+group.getName() +", "+group.getMembers().size());
            return group;
        }
    }
}
